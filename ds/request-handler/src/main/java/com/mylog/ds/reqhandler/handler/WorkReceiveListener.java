package com.mylog.ds.reqhandler.handler;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class WorkReceiveListener {


    /**
     * 监听器 监听queue_work队列的字符串消息
     * @param msg
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "queue_work")
    public void receiveMessage1(String msg, Channel channel, Message message){
        System.out.println("接收器01接收到Channel : " + channel);
        System.out.println("接收器01接收到Message body : " + new String(message.getBody()));
        System.out.println("接收器01接收到消息 : " + msg);
    }


    /**
     * 监听器 监听queue_work队列的对象消息
     * @param obj
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj, Channel channel, Message message){
        System.out.println("接收器02接收到Channel : " + channel);
        System.out.println("接收器02接收到Message body : " + new String(message.getBody()));
        System.out.println("接收器02接收到消息 : " + obj);
    }


    /**
     * 监听器 监听queue_fanout1队列
     * @param msg
     */
    @RabbitListener(queues = "queue_fanout1")
    public void receiveMsg1(String msg){
        System.out.println("收到队列1中的消息 : " + msg);
    }


    /**
     * 监听器 监听queue_fanout2队列
     * @param msg
     */
    @RabbitListener(queues = "queue_fanout2")
    public void receiveMsg2(String msg){
        System.out.println("收到队列2中的消息 : " + msg);
    }


}