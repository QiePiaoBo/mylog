package com.mylog.business.chat.consumer;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.mq.LogicerTalkMqConstant;
import com.dylan.protocol.logicer.LogicerConstant;
import com.dylan.protocol.logicer.LogicerContent;
import com.dylan.protocol.logicer.LogicerMessage;
import com.dylan.protocol.logicer.LogicerSubProtocol;
import com.dylan.protocol.logicer.LogicerTalkWord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Queues;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.business.chat.model.converter.MsgRecordConverter;
import com.mylog.business.chat.service.SessionService;
import com.mylog.tools.utils.utils.Safes;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname LogicerTalkListener
 * @Description LogicerTalkListener
 * @Date 6/17/2022 3:21 PM
 */
@Component
public class LogicerTalkListener {

    private final MyLogger logger = MyLoggerFactory.getLogger(LogicerTalkListener.class);

    @Resource
    private SessionService sessionService;

    @Resource
    private MsgRecordMapper msgRecordMapper;

    @Resource
    @Qualifier("msgCollectExecutor")
    private ThreadPoolExecutor msgCollectExecutor;

    private boolean logicerTalkBegin = true;

    @Value("${logicerTalkMsg.consumeFlag: true}")
    private boolean consumeFlag;


    @RabbitListener(queues = {LogicerTalkMqConstant.LOGICER_QUEUE_TALK})
    public void receiveMsg(String msg) {
        try {
            LogicerMessage logicerMessage = LogicerConstant.COMMON_OBJECT_MAPPER.readValue(msg, LogicerMessage.class);
            if (logicerMessage.getLogicerHeader().getLogicerProtocol().getCode() != LogicerSubProtocol.TALK.getCode()) {
                logger.error("<LogicerTalkListener> Can not handle msg : {} because sub protocol error.", msg);
                return;
            }
            logger.info("<LogicerTalkListener> Listener {} received message:{}, parsed into {}", "LogicerTalkListener", msg, logicerMessage);
            try {
                MsgCollectConstant.MSG_COLLECT_QUEUE.put(logicerMessage);
            } catch (InterruptedException e) {
                logger.error("<LogicerTalkListener> Error putting {}", logicerMessage);
            }
            if (logicerTalkBegin) {
                startLogicerTalkCollect();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing {}", msg);
        }
    }

    public void startLogicerTalkCollect() {
        Runnable runnable = () -> {
            logger.info("<LogicerTalkListener> starting logicerTalkCollect");
            while (true) {
                List<LogicerMessage> list = new ArrayList<>();
                try {
                    Queues.drain(MsgCollectConstant.MSG_COLLECT_QUEUE, list, 100, 1, TimeUnit.MINUTES);
                    if (list.size() > 0) {
                        // 根据LogicerMessage列表获取所有的相关userName
                        List<String> names = Safes.of(list).stream()
                                .map(LogicerMessage::getLogicerContent)
                                .map(LogicerContent::getActionWord)
                                .map(m -> {
                                    try {
                                        return LogicerConstant.COMMON_OBJECT_MAPPER.readValue(m, LogicerTalkWord.class);
                                    } catch (JsonProcessingException e) {
                                        logger.error("Error reading value of {}", m);
                                        return null;
                                    }
                                })
                                .filter(Objects::nonNull)
                                .flatMap(n -> Stream.of(n.getFrom(), n.getTo()))
                                .distinct()
                                .collect(Collectors.toList());
                        // 根据所有的userName获取每个userName和Id的对应关系
                        Map<String, Integer> userNameIds = sessionService.getUserNameIdMap(names);
                        // 根据以上信息组装消息插入对象
                        List<MsgInsertModel> msgInsertModels = Safes.of(list)
                                .stream()
                                .map(m -> {
                                    LogicerTalkWord logicerTalkWord = null;
                                    try {
                                        logicerTalkWord = LogicerConstant.COMMON_OBJECT_MAPPER.readValue(m.getLogicerContent().getActionWord(), LogicerTalkWord.class);
                                    } catch (JsonProcessingException e) {
                                        logger.error("<LogicerTalkListener> Error talkWord, LogicerMessage: {}", m);
                                        return null;
                                    }
                                    Integer msgAreaType = 0;
                                    if (StringUtils.isNumeric(logicerTalkWord.getType())){
                                        msgAreaType = Integer.parseInt(logicerTalkWord.getType());
                                    }
                                    return MsgRecordConverter.getInsertModel(m,
                                            userNameIds.getOrDefault(logicerTalkWord.getFrom(), null),
                                            userNameIds.getOrDefault(logicerTalkWord.getTo(), null),
                                            msgAreaType);
                                })
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
                        // 插入消息
                        Integer insertNum = msgRecordMapper.batchInsertMsgRecord(msgInsertModels);
                        if (insertNum > 0) {
                            logger.info("Success, {} batch of messages inserted.", insertNum);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        msgCollectExecutor.execute(runnable);
        // 线程只启动一次
        logicerTalkBegin = false;
    }

}
