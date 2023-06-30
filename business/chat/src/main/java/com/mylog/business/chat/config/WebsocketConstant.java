package com.mylog.business.chat.config;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname WebsocketConstant
 * @Description WebsocketConstant
 * @Date 4/27/2023 2:44 PM
 */
public class WebsocketConstant {


    public static final AtomicInteger WS_ONLINE_NUM = new AtomicInteger();

    /**
     * WS属性 用户名
     */
    public static final String WS_PROPERTIES_USERNAME = "WS_PROPERTIES_USERNAME";

    /**
     * WS属性 会话接收者
     */
    public static final String WS_PROPERTIES_TALKWITH = "WS_PROPERTIES_TALKWITH";

    /**
     * WS属性 会话Id
     */
    public static final String WS_PROPERTIES_SESSIONID = "WS_PROPERTIES_SESSIONID";

    /**
     * userName - session
     */
    public static final ConcurrentHashMap<String, WebSocketSession> WS_SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * userName - LgcTalk会话Id
     */
    public static final ConcurrentHashMap<String, Integer> WS_USERNAME_SESSIONID_POOL = new ConcurrentHashMap<>();


}
