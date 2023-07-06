package com.mylog.business.chat.ws;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.config.WebsocketConstant;
import com.mylog.tools.model.model.exception.MyException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname WebSocketUtil
 * @Description WebSocketUtil
 * @Date 4/27/2023 3:27 PM
 */
public class WebSocketUtil {

    private static final Pattern PATTERN_BETWEEN_TWO_CHARACTER = Pattern.compile("\\<@(.*?)\\>");
    private static final String AIMING_NAME_START_CHARACTER = "<@";

    private static final MyLogger logger = MyLoggerFactory.getLogger(WebSocketUtil.class);

    /**
     * 根据用户名断开连接
     * @param userName
     */
    public static void disconnectForUser(String userName){
        WebSocketSession socketSession = WebsocketConstant.WS_SESSION_POOL.getOrDefault(userName, null);
        if (Objects.nonNull(socketSession)){
            try {
                socketSession.sendMessage(new TextMessage("即将断开连接"));
                socketSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WebsocketConstant.WS_SESSION_POOL.remove(userName);
        WebsocketConstant.WS_ONLINE_NUM.decrementAndGet();
    }

    /**
     * 获取报文中要@的人的名字
     * @param input
     * @return
     */
    public static String getMessageAimingUser(String input) {
        Matcher matcher = PATTERN_BETWEEN_TWO_CHARACTER.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * 获取报文中真实的业务内容
     * @param input
     * @return
     */
    public static String getCompleteMsg(String input) {
        if (input.contains(AIMING_NAME_START_CHARACTER)){
            return input.substring(0,input.indexOf(AIMING_NAME_START_CHARACTER));
        }
        return null;
    }

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
     * @param toUserName
     * @param message
     */
    public static void sendToUser(String fromUserName, String toUserName, String message){
        WebSocketSession socketSession = WebsocketConstant.WS_SESSION_POOL.getOrDefault(toUserName, null);
        if (Objects.isNull(socketSession)){
            return;
        }
        try {
            logger.info("<sendToUser> Sending msg... {} : {}", fromUserName, message);
            socketSession.sendMessage(new TextMessage(fromUserName + ": " + message));
        } catch (IOException e) {
            throw new MyException(e);
        }

    }


}
