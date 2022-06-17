package com.mylog.business.chat.consumer;

import com.mylog.business.chat.constant.LogicerTalkMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname LogicerTalkListener
 * @Description LogicerTalkListener
 * @Date 6/17/2022 3:21 PM
 */
@Component
@RabbitListener(queues = {LogicerTalkMQConstant.LOGICER_QUEUE_TALK})
public class LogicerTalkListener {

    private final Logger logger = LoggerFactory.getLogger(LogicerTalkListener.class);

    @RabbitHandler
    public void receiveMsg(String msg){
        logger.info("class:{},message:{}", "LogicerTalkListener", msg);
    }


}
