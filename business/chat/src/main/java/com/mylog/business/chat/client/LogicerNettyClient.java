package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.LogicerMessageBuilder;
import com.dylan.protocol.logicer.LogicerTalkWord;
import com.dylan.protocol.logicer.LogicerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylog.business.chat.config.NettyClientConstant;
import com.mylog.business.chat.ws.WebSocketUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang.StringUtils;

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

    private String userName;

    private String password;

    private Bootstrap bootstrap;

    private EventLoopGroup group;

    private Channel ch;

    public LogicerNettyClient(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.init();
    }

    private void init() {
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                .handler(new LogicerNettyClientInitializer());
    }

    public void connect(String serverAddr, Integer port) throws InterruptedException {
        try {
            if (Objects.nonNull(ch) && ch.isOpen()){
                return;
            }
            // Start the connection attempt.
            ch = bootstrap.connect(serverAddr, port).sync().channel();
            ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(getUserName() + "@" + password));
            while (Objects.nonNull(NettyClientConstant.USER_NETTY_CLIENT_CENTER.getOrDefault(getUserName(), null))){
                Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(getUserName(), null);
                if (Objects.nonNull(messageCenter) && messageCenter.size() > 0){
                    String nextLine = messageCenter.pop();
                    logger.info("即将发送：" + nextLine);
                    String realMsg = WebSocketUtil.getCompleteMsg(nextLine);
                    if (Objects.isNull(realMsg)){
                        continue;
                    }
                    if ("exit".equals(realMsg)){
                        logger.info("即将断开连接");
                        ch.writeAndFlush(LogicerMessageBuilder.buildExitMessage());
                        TimeUnit.SECONDS.sleep(1);
                        ch.close();
                        break;
                    } else if ("connect".equals(realMsg)) {
                        connect(serverAddr, port);
                        break;
                    }
                    if (LogicerUtil.isLoginStr(realMsg)){
                        ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(realMsg));
                    }else {
                        // 不是登录类型的消息 就默认使用talk子协议进行发送
                        // ch.writeAndFlush(LogicerMessageBuilder.buildMessage(nextLine));
                        LogicerTalkWord msg = new LogicerTalkWord();
                        msg.setType("0");
                        String messageAimingUser = WebSocketUtil.getMessageAimingUser(nextLine);
                        if (StringUtils.isNotBlank(messageAimingUser) && StringUtils.isNotBlank(realMsg)){
                            logger.info("msg: {}, to: {}", realMsg, messageAimingUser);
                            msg.setTo(messageAimingUser);
                            msg.setMsg(realMsg);
                            ch.writeAndFlush(LogicerMessageBuilder.buildMessage(new ObjectMapper().writeValueAsString(msg)));
                        }
                    }
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (JsonProcessingException e) {
            logger.error("Connection failed: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            logger.error("Connection closing. {}:{}, current user is : {}", serverAddr, port, getUserName());
            group.shutdownGracefully();
        }
    }


    public String getUserName() {
        return userName;
    }

    public EventLoopGroup getGroup() {
        return group;
    }
}
