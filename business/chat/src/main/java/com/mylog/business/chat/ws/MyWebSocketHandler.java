package com.mylog.business.chat.ws;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.LogicerUtil;
import com.mylog.business.chat.client.LogicerNettyClientBuildService;
import com.mylog.business.chat.client.LogicerNettyClientUtil;
import com.mylog.business.chat.config.NettyClientConstant;
import com.mylog.business.chat.config.WebSocketInterceptor;
import com.mylog.business.chat.config.WebsocketConstant;
import com.mylog.tools.model.model.exception.MyException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
        String userName = getUserName(session, WebsocketConstant.WS_PROPERTIES_USERNAME);
        String completeMsg = WebSocketUtil.getCompleteMsg(messagePayload);
        logger.info("handling textMessage ---> {}: {}【{}】", userName, messagePayload, completeMsg);
        if (Objects.isNull(completeMsg)){
            return;
        }
        // 将接收到的报文发送给netty服务
        if (LogicerUtil.isLoginStr(completeMsg)){
            String[] split = completeMsg.split("@");
            // 创建netty客户端并将本条报文透传到netty服务
            if (completeMsg.contains(userName + "@") && split.length == 2){
                logicerNettyClientBuildService.startConnection(userName, split[1]);
            }
        }else {
            // session.sendMessage(new TextMessage(String.format("Got message %s from %s", messagePayload, userName)));
            // 发送消息
            // todo 在messagePayLoad中 添加session值
            LogicerNettyClientUtil.sendMessage(userName, messagePayload);
        }
    }

    /**
     * 从session中获取userName
     * @param session
     * @return
     */
    private String getUserName(WebSocketSession session, String aimKey) {
        Object websocket_username = session.getAttributes().get(aimKey);
        String userName = "";
        if (Objects.nonNull(websocket_username) && websocket_username instanceof String){
            userName = (String) websocket_username;
        }
        // logger.info("UserName is {}", userName);
        return userName;
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
        String userName = getUserName(session, WebsocketConstant.WS_PROPERTIES_USERNAME);
        if (WebsocketConstant.WS_SESSION_POOL.containsKey(userName)){
            WebsocketConstant.WS_SESSION_POOL.remove(userName);
            subOnlineCount();
            LogicerNettyClientUtil.userLogout(userName);
        }
        logger.info("{} disconnect!", sessionId);
    }

}
