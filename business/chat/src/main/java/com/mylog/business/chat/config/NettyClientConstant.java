package com.mylog.business.chat.config;

import io.netty.channel.EventLoopGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Classname NettyClientConstant
 * @Description NettyClientConstant
 * @Date 4/27/2023 11:22 AM
 */
public class NettyClientConstant {

    public static final Map<String, Stack<String>> USER_MESSAGE_CENTER = new HashMap<>();

    public static final Map<String, EventLoopGroup> USER_GROUP_CENTER = new HashMap<>();

}
