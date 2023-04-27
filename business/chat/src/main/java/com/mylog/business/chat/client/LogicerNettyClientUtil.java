package com.mylog.business.chat.client;

import com.mylog.business.chat.config.NettyClientConstant;
import io.netty.channel.EventLoopGroup;

import java.util.Objects;
import java.util.Stack;

/**
 * @Classname LogicerNettyClientUtil
 * @Description LogicerNettyClientUtil
 * @Date 4/27/2023 3:09 PM
 */
public class LogicerNettyClientUtil {

    /**
     * 发送消息
     * @param userName
     * @param message
     */
    public static void sendMessage(String userName, String message){
        Stack<String> userMessageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(userName, null);
        if (Objects.nonNull(userMessageCenter)){
            userMessageCenter.push(message);
        }
    }

    /**
     * 用户登出
     * @param userName
     */
    public static void userLogout(String userName){
        EventLoopGroup eventLoopGroup = NettyClientConstant.USER_GROUP_CENTER.getOrDefault(userName, null);
        if (Objects.nonNull(eventLoopGroup)){
            eventLoopGroup.shutdownGracefully();
        }
    }


}
