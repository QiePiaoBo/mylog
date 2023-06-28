package com.mylog.business.chat.consumer;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.mq.LogicerTalkMqConstant;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname LogicerTalkListener
 * @Description LogicerTalkListener
 * @Date 6/17/2022 3:21 PM
 */
@Component
public class LogicerTalkListener {

    private final MyLogger logger = MyLoggerFactory.getLogger(LogicerTalkListener.class);


    @Resource
    private MsgRecordMapper msgRecordMapper;


    @RabbitListener(queues = {LogicerTalkMqConstant.LOGICER_QUEUE_TALK})
    public void receiveMsg(String msg){
        // todo 添加TALK类型消息的处理逻辑（入库）
        logger.info("class:{},message:{}", "LogicerTalkListener", msg);
    }

}
