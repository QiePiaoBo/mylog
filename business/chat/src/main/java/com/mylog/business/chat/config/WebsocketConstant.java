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
     * userName - session
     */
    public static final ConcurrentHashMap<String, WebSocketSession> WS_SESSION_POOL = new ConcurrentHashMap<>();


}
