package com.mylog.business.chat.ws;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.LogicerUtil;
import com.mylog.business.chat.client.LogicerNettyClientBuildService;
import com.mylog.business.chat.client.LogicerNettyClientUtil;
import com.mylog.business.chat.config.WebsocketConstant;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Classname MyWebSocketHandler
 * @Description MyWebSocketHandler
 * @Date 7/6/2022 3:55 PM
 */
@Service
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final MyLogger logger = MyLoggerFactory.getLogger(MyWebSocketHandler.class);

    @Resource
    private LogicerNettyClientBuildService logicerNettyClientBuildService;

    /**
     * 在线人数+1
     */
    public static void addOnlineCount(){
        WebsocketConstant.WS_ONLINE_NUM.incrementAndGet();
    }

    /**
     * 在线人数减一
     */
    public static void subOnlineCount(){
        WebsocketConstant.WS_ONLINE_NUM.decrementAndGet();
    }


    /**
     * 接受客户端消息并发送到Netty服务器
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String messagePayload = message.getPayload();
        String userName = getSessionProperty(session, WebsocketConstant.WS_PROPERTIES_USERNAME);
        String sessionId = getSessionProperty(session, WebsocketConstant.WS_PROPERTIES_SESSIONID);
        String completeMsg = WebSocketUtil.getCompleteMsg(messagePayload);
        logger.info("handling textMessage ---> {}&{}: {}【{}】", userName, sessionId, messagePayload, completeMsg);
        if (Objects.isNull(completeMsg)){
            return;
        }
        // 将接收到的报文发送给netty服务
        if (LogicerUtil.isLoginStr(completeMsg)){
            String[] split = completeMsg.split("@");
            // 创建netty客户端并将本条报文透传到netty服务
            if (completeMsg.contains(userName + "@") && split.length == 2){
                logicerNettyClientBuildService.startConnection(userName, split[1], sessionId);
            }
        }else {
            // session.sendMessage(new TextMessage(String.format("Got message %s from %s", messagePayload, userName)));
            // 发送消息
            LogicerNettyClientUtil.sendMessage(userName, messagePayload);
        }
    }

    /**
     * 从session中获取目的属性值
     * @param session
     * @param aimKey
     * @return
     */
    private String getSessionProperty(WebSocketSession session, String aimKey) {
        Object websocket_property = session.getAttributes().get(aimKey);
        String property = "";
        if (Objects.nonNull(websocket_property) && websocket_property instanceof String){
            property = (String) websocket_property;
        }
        // logger.info("UserName is {}", userName);
        return property;
    }

    /**
     * 连接建立时
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object websocket_username = session.getAttributes().get(WebsocketConstant.WS_PROPERTIES_USERNAME);
        String userName = "";
        if (Objects.nonNull(websocket_username) && websocket_username instanceof String){
            userName = (String) websocket_username;
        }
        // logger.info("UserName is {}", userName);
        WebSocketSession put = WebsocketConstant.WS_SESSION_POOL.put(userName, session);
        if (Objects.isNull(put)){
            addOnlineCount();
        }
        session.sendMessage(new TextMessage("请输入用户名@密码以登录，示例：lingling@123456"));
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
        String userName = getSessionProperty(session, WebsocketConstant.WS_PROPERTIES_USERNAME);
        if (WebsocketConstant.WS_SESSION_POOL.containsKey(userName)){
            WebsocketConstant.WS_SESSION_POOL.remove(userName);
            subOnlineCount();
            LogicerNettyClientUtil.userLogout(userName);
        }
        logger.info("{} disconnect!", sessionId);
    }

}
