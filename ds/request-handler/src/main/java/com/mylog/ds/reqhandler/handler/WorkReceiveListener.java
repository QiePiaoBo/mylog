package com.mylog.ds.reqhandler.handler;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WorkReceiveListener {

    @RabbitListener(queues = "queue_work")
    public void receiveMessage1(String msg, Channel channel, Message message){
        System.out.println("接收器01接收到消息 : " + msg);
    }


    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj, Channel channel, Message message){

        System.out.println("接收器02接收到消息 : " + obj);
    }


    @RabbitListener(queues = "queue_fanout1")
    public void receiveMsg1(String msg){
        System.out.println("收到队列1中的消息 : " + msg);
    }


    @RabbitListener(queues = "queue_fanout2")
    public void receiveMsg2(String msg){
        System.out.println("收到队列2中的消息 : " + msg);
    }


}
