package com.mylog.business.chat.config;

import com.mylog.business.chat.client.LogicerNettyClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Classname NettyClientConstant
 * @Description NettyClientConstant
 * @Date 4/27/2023 11:22 AM
 */
public class NettyClientConstant {

    /**
     * startUser&->talkWith - 会话消息栈
     */
    public static final Map<String, Stack<String>> USER_MESSAGE_CENTER = new HashMap<>();

    /**
     * startUser&->talkWith - netty客户端
     */
    public static final Map<String, LogicerNettyClient> USER_NETTY_CLIENT_CENTER = new HashMap<>();
}
