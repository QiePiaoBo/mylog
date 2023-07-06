package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylog.business.chat.config.BusinessClientDTO;
import com.mylog.business.chat.config.NettyClientConstant;
import com.mylog.business.chat.config.WebsocketConstant;
import com.mylog.business.chat.ws.WebSocketUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @Classname LogicerNettyClient
 * @Description LogicerNettyClient
 * @Date 4/27/2023 11:02 AM
 */
public class LogicerNettyClient {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LogicerNettyClient.class);

    private final String userName;

    private final String password;

    private final String sessionId;

    private final String msgAreaType;

    private Bootstrap bootstrap;

    private EventLoopGroup group;

    private Channel ch;

    public LogicerNettyClient(String userName, String password, String sessionId, String msgAreaType) {
        this.userName = userName;
        this.password = password;
        this.sessionId = sessionId;
        this.msgAreaType = msgAreaType;
        this.init();
    }

    private void init() {
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        BusinessClientDTO dto = BusinessClientDTO
                .builder()
                .sessionId(getSessionId())
                .userName(getUserName())
                .password(getPassword())
                .msgAreaType(getMsgAreaType())
                .build();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                .handler(new LogicerNettyClientInitializer(dto));
    }

    public void connect(String serverAddr, Integer port) throws InterruptedException {
        try {
            if (Objects.nonNull(ch) && ch.isOpen()){
                return;
            }
            // Start the connection attempt.
            ch = bootstrap.connect(serverAddr, port).sync().channel();
            ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(getUserName() + "@" + password, getSessionId()));
            while (Objects.nonNull(NettyClientConstant.USER_NETTY_CLIENT_CENTER.getOrDefault(getUserName(), null))){
                if (!ch.isOpen()){
                    logger.error("<LogicerNettyClient> Channel closed. UserName: {}", getUserName());
                    NettyClientConstant.USER_NETTY_CLIENT_CENTER.remove(getUserName());
                    WebSocketUtil.disconnectForUser(getUserName());
                }
                Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(getUserName(), null);
                if (Objects.nonNull(messageCenter) && messageCenter.size() > 0){
                    String nextLine = messageCenter.pop();
                    //logger.info("即将发送：" + nextLine);
                    String realMsg = WebSocketUtil.getCompleteMsg(nextLine);
                    if (Objects.isNull(realMsg)){
                        continue;
                    }
                    if (LogicerUtil.isLoginStr(realMsg)){
                        ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(realMsg, getSessionId()));
                    }else if (realMsg.startsWith(LogicerConstant.COMMAND_MSG_START_STR)){
                        // 如果是指令类型的消息 就组装指令消息
                        LogicerMessage commandMessage = LogicerMessageBuilder.buildMessage(2, realMsg, getSessionId());
                        ch.writeAndFlush(commandMessage);
                    } else {
                        // 不是登录类型的消息 就默认使用talk子协议进行发送
                        // ch.writeAndFlush(LogicerMessageBuilder.buildMessage(nextLine, getSessionId()));
                        LogicerTalkWord msg = new LogicerTalkWord();
                        msg.setType(getMsgAreaType());
                        msg.setFrom(getUserName());
                        String messageAimingUser = WebSocketUtil.getMessageAimingUser(nextLine);
                        if (StringUtils.isNotBlank(messageAimingUser) && StringUtils.isNotBlank(realMsg)){
                            logger.info("<LogicerNettyClient> msg: {}, to: {}", realMsg, messageAimingUser);
                            msg.setTo(messageAimingUser);
                            msg.setMsg(realMsg);
                            ch.writeAndFlush(LogicerMessageBuilder.buildMessage(new ObjectMapper().writeValueAsString(msg), getSessionId()));
                        }
                    }
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (JsonProcessingException e) {
            logger.error("Connection failed: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            logger.info("Connection closing. {}:{}, current user is : {}", serverAddr, port, getUserName());
            group.shutdownGracefully();
        }
    }


    public String getUserName() {
        return userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public String getPassword() {
        return password;
    }

    public String getMsgAreaType() {
        return msgAreaType;
    }
}
