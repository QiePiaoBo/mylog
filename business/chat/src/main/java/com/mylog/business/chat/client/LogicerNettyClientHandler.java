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
import com.mylog.business.chat.ws.MyWebSocketHandler;
import com.mylog.business.chat.ws.WebSocketUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Dylan
 * @Date : 2022/4/13 - 15:10
 */
@Component
public class LogicerNettyClientHandler extends SimpleChannelInboundHandler<LogicerMessage> {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LogicerNettyClientHandler.class);

    @Resource
    private MyWebSocketHandler myWebSocketHandler;


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
            logger.info("Server: {}", logicerMessage.getLogicerContent().getActionWord());
            if (LogicerConstant.ASK_FOR_LOGIN_INFO.equals(logicerMessage.getLogicerContent().toString())){
                logger.info("Please write your name and password like [name@password]!");
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
                String to = jsonNode.get("to").textValue();
                if (!StringUtils.isEmpty(to)){
                    WebSocketUtil.sendToUser(to, jsonNode.get("msg").textValue());
                }
            }catch (JsonProcessingException e){
                logger.error("{}", e.getMessage(), e);
            }
        }
        if (LogicerSubProtocol.COMMAND.equals(logicerProtocol)){
            logger.info("Got command msg: {}", logicerMessage.getLogicerContent());
            String actionWord = logicerMessage.getLogicerContent().getActionWord();
            if (actionWord.equals("BYE")){
                channelHandlerContext.channel().closeFuture();
            }
        }
    }
}
