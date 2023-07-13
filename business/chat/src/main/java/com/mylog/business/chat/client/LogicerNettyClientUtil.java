package com.mylog.business.chat.client;

import com.mylog.business.chat.config.NettyClientConstant;
import com.mylog.business.chat.config.WebsocketConstant;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Classname LogicerNettyClientUtil
 * @Description LogicerNettyClientUtil
 * @Date 4/27/2023 3:09 PM
 */
public class LogicerNettyClientUtil {

    /**
     * 发送消息
     * @param conversationMapKey 会话唯一键
     * @param message
     */
    public static void sendMessage(String conversationMapKey, String message) throws InterruptedException {
        LinkedBlockingQueue<String> userMessageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(conversationMapKey, null);
        if (Objects.nonNull(userMessageCenter)){
            userMessageCenter.put(message);
        }
    }

    /**
     * 用户登出
     * @param conversationMapKey
     */
    public static void userLogout(String conversationMapKey){
        // 这里传入的userName参数就是 ConversationUtil.getConversationMapKey的值
        LogicerNettyClient savedClient = NettyClientConstant.USER_NETTY_CLIENT_CENTER.getOrDefault(conversationMapKey, null);
        if (Objects.nonNull(savedClient)){
            // 清理连接netty服务端的资源
            // 关闭与netty服务端的连接
            savedClient.getGroup().shutdownGracefully();
            // 删除当前用户-客户端
            NettyClientConstant.USER_NETTY_CLIENT_CENTER.remove(conversationMapKey);
            // 删除当前用户-消息栈
            NettyClientConstant.USER_MESSAGE_CENTER.remove(conversationMapKey);
        }
    }


}
