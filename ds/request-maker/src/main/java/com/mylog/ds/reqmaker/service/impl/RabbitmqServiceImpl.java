package com.mylog.ds.reqmaker.service.impl;

import com.mylog.ds.reqmaker.service.RabbitmqService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {


    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public void sendWork() {
        // 向单个队列中去写消息
        rabbitTemplate.convertAndSend("queue_work", "测试work模型_01");
        // MessagePropertiesBuilder.newInstance().setContentType("text/plain").build()
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        rabbitTemplate.send("queue_work", new Message("测试work模型_02".getBytes(StandardCharsets.UTF_8),MessagePropertiesBuilder.newInstance().setContentType("text/plain").build()));
    }


    @Override
    public void sendPublish() {
        // 向交换机中发送消息 其实在交换机中的两个队列都会接收到这里的消息
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange_fanout","", "测试发布订阅模型 : " + i);
        }
    }


}
