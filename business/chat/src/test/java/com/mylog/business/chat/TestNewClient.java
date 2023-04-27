package com.mylog.business.chat;

import com.mylog.business.chat.client.LogicerNettyClient;
import com.mylog.business.chat.config.NettyClientConstant;
import io.netty.channel.EventLoopGroup;
import org.junit.Test;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Classname TestNewClient
 * @Description TestNewClient
 * @Date 4/27/2023 11:28 AM
 */
public class TestNewClient {


    @Test
    public void testNewClient() throws InterruptedException {
        LogicerNettyClient logicerNettyClient = new LogicerNettyClient("duke", "123456");
        String userName = logicerNettyClient.getUserName();
        //  为userName注册消息中心
        Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(messageCenter)){
            messageCenter = new Stack<>();
            NettyClientConstant.USER_MESSAGE_CENTER.put(userName, messageCenter);
        }
        // 为userName注册EventLoopGroup
        EventLoopGroup eventLoopGroup = NettyClientConstant.USER_GROUP_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(eventLoopGroup)){
            eventLoopGroup = logicerNettyClient.getGroup();
            NettyClientConstant.USER_GROUP_CENTER.put(userName, eventLoopGroup);
        }
        Runnable runnable = new Runnable() {
            /**
             * When an object implementing interface <code>Runnable</code> is used
             * to create a thread, starting the thread causes the object's
             * <code>run</code> method to be called in that separately executing
             * thread.
             * <p>
             * The general contract of the method <code>run</code> is that it may
             * take any action whatsoever.
             *
             * @see Thread#run()
             */
            @Override
            public void run() {
                try {
                    logicerNettyClient.connect("logicer.top", 8001);
                }catch (Exception e){

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        int i = 0;
        while (i < 100){
            i++;
            Thread.sleep(1000);
            messageCenter.push("hello ");
        }
        messageCenter.push("exit");

    }

}
