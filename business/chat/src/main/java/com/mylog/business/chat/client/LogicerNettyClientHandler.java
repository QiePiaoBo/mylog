package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.LogicerConstant;
import com.dylan.protocol.logicer.LogicerHeader;
import com.dylan.protocol.logicer.LogicerMessage;
import com.dylan.protocol.logicer.LogicerMessageBuilder;
import com.dylan.protocol.logicer.LogicerSubProtocol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylog.business.chat.config.WebsocketConstant;
import com.mylog.business.chat.ws.WebSocketUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Dylan
 * @Date : 2022/4/13 - 15:10
 */
@Component
public class LogicerNettyClientHandler extends SimpleChannelInboundHandler<LogicerMessage> {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LogicerNettyClientHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogicerMessage logicerMessage){
        if (Objects.isNull(logicerMessage)){
            return;
        }
        Channel channel = channelHandlerContext.channel();
        LogicerHeader logicerHeader = logicerMessage.getLogicerHeader();
        LogicerSubProtocol logicerProtocol = logicerHeader.getLogicerProtocol();
        // 如果是心跳报文
        if (LogicerSubProtocol.HEARTBEAT.equals(logicerProtocol)){
            // logger.debug("Server: {}", logicerMessage.getLogicerContent().getActionWord());
            // 如果content是心跳检测字符串 就发送对应的回应报文
            if (LogicerConstant.LOGICER_STATE_ASK.equals(logicerMessage.getLogicerContent().getActionWord())){
                channel.writeAndFlush(LogicerMessageBuilder.buildMessage(3, LogicerConstant.LOGICER_STATE_ANSWER));
            }
        }
        // 如果是Logicer报文
        if (LogicerSubProtocol.LOGICER.equals(logicerProtocol)){
            String actionWord = logicerMessage.getLogicerContent().getActionWord();
            logger.info("Server: {}", actionWord);
            if (LogicerConstant.ASK_FOR_LOGIN_INFO.equals(logicerMessage.getLogicerContent().toString())){
                logger.info("Please write your name and password like [name@password]!");
            }
            String messageAimingUser = WebSocketUtil.getMessageAimingUser(actionWord);
            String completeMsg = WebSocketUtil.getCompleteMsg(actionWord);
            if (!StringUtils.isEmpty(messageAimingUser) && !StringUtils.isEmpty(completeMsg)){
                // 如果系统@全体成员 就对所有人发送消息 否则就对指定的人发送消息
                if (messageAimingUser.equals("@all")){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Enumeration<String> keys = WebsocketConstant.WS_SESSION_POOL.keys();
                    while (keys.hasMoreElements()){
                        String s = keys.nextElement();
                        WebSocketUtil.sendToUser("Server", s, completeMsg);
                    }
                }else {
                    WebSocketUtil.sendToUser("Server", messageAimingUser, completeMsg);
                }
            }
        }
        // 如果是talk类型的消息
        if (LogicerSubProtocol.TALK.equals(logicerProtocol)){
            // logger.debug("server: {}", logicerMessage.getLogicerContent().getActionWord());
            String actionWord = logicerMessage.getLogicerContent().getActionWord();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(actionWord);
                logger.info("#{}: {}", jsonNode.get("from").textValue(), jsonNode.get("msg").textValue());
                String from = jsonNode.get("from").textValue();
                String to = jsonNode.get("to").textValue();
                if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to)){
                    WebSocketUtil.sendToUser(from, to, jsonNode.get("msg").textValue());
                }
            }catch (JsonProcessingException e){
                logger.error("{}", e.getMessage(), e);
            }
        }
        if (LogicerSubProtocol.COMMAND.equals(logicerProtocol)){
            logger.info("Got command msg: {}", logicerMessage.getLogicerContent());
            String actionWord = logicerMessage.getLogicerContent().getActionWord();
            if (!StringUtils.isEmpty(actionWord) && actionWord.equals("BYE")){
                channelHandlerContext.channel().closeFuture();
            }
        }
    }
}
