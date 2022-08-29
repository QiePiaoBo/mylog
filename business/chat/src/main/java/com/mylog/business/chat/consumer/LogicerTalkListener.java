package com.mylog.business.chat.consumer;

import com.dylan.mq.LogicerTalkMqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname LogicerTalkListener
 * @Description LogicerTalkListener
 * @Date 6/17/2022 3:21 PM
 */
@Component
public class LogicerTalkListener {

    private final Logger logger = LoggerFactory.getLogger(LogicerTalkListener.class);

    @RabbitListener(queues = {LogicerTalkMqConstant.LOGICER_QUEUE_TALK})
    public void receiveMsg(String msg){
        // todo 添加TALK类型消息的处理逻辑（入库）
        logger.info("class:{},message:{}", "LogicerTalkListener", msg);
    }

}
