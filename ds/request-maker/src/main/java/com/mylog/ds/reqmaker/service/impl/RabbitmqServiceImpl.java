package com.mylog.ds.reqmaker.service.impl;

import com.mylog.ds.reqmaker.service.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendWork() {
        rabbitTemplate.convertAndSend("queue_work", "测试work模型");
    }

    @Override
    public void sendPublish() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange_fanout","", "测试发布订阅模型 : " + i);
        }
    }
}
