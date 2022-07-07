package com.mylog.business.chat.ws;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.config.WebSocketInterceptor;
import com.mylog.tools.model.model.exception.MyException;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname MyWebSocketHandler
 * @Description MyWebSocketHandler
 * @Date 7/6/2022 3:55 PM
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final MyLogger logger = MyLoggerFactory.getLogger(MyWebSocketHandler.class);

    private static final AtomicInteger onlineNum = new AtomicInteger();

    private static final ConcurrentHashMap<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

    /**
     * 在线人数+1
     */
    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    /**
     * 在线人数减一
     */
    public static void subOnlineCount(){
        onlineNum.decrementAndGet();
    }


    /**
     * 接受客户端消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // todo 将接收到的报文转化成logicer报文发送给
        session.sendMessage(new TextMessage(
                String.format("Got message %s from %s", message.getPayload(), session.getId()))
        );
    }

    /**
     * 连接建立时
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object websocket_username = session.getAttributes().get("WEBSOCKET_USERNAME");
        String userName = "";
        if (Objects.nonNull(websocket_username) && websocket_username instanceof String){
            userName = (String) websocket_username;
        }
        logger.info("UserName is {}", userName);
        WebSocketSession put = sessionPool.put(session.getId(), session);
        if (Objects.isNull(put)){
            addOnlineCount();
        }
    }

    /**
     * 连接关闭后
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        if (sessionPool.containsKey(sessionId)){
            sessionPool.remove(sessionId);
            subOnlineCount();
        }
        logger.info("{} disconnect!", sessionId);
    }

    /**
     * 群发消息
     * @param message
     */
    public static void sendTopic(String message){
        if (sessionPool.isEmpty()){
            return;
        }
        for (Map.Entry<String, WebSocketSession> entry : sessionPool.entrySet()){
            try {
                entry.getValue().sendMessage(new TextMessage(message));
            }catch (IOException e){
                throw new MyException(e);
            }
        }
    }

    /**
     * 点对点发送
     * @param uid
     * @param message
     */
    public static void sendToUser(String uid, String message){
        WebSocketSession socketSession = sessionPool.getOrDefault(uid, null);
        if (Objects.isNull(socketSession)){
            return;
        }
        try {
            socketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new MyException(e);
        }

    }


}
