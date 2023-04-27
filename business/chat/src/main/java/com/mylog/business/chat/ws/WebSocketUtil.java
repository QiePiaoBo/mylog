package com.mylog.business.chat.ws;

import com.mylog.business.chat.config.WebsocketConstant;
import com.mylog.tools.model.model.exception.MyException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @Classname WebSocketUtil
 * @Description WebSocketUtil
 * @Date 4/27/2023 3:27 PM
 */
public class WebSocketUtil {


    /**
     * 群发消息
     * @param message
     */
    public static void sendTopic(String message){
        if (WebsocketConstant.WS_SESSION_POOL.isEmpty()){
            return;
        }
        for (Map.Entry<String, WebSocketSession> entry : WebsocketConstant.WS_SESSION_POOL.entrySet()){
            try {
                entry.getValue().sendMessage(new TextMessage(message));
            }catch (IOException e){
                throw new MyException(e);
            }
        }
    }

    /**
     * 点对点发送
     * @param userName
     * @param message
     */
    public static void sendToUser(String userName, String message){
        WebSocketSession socketSession = WebsocketConstant.WS_SESSION_POOL.getOrDefault(userName, null);
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
