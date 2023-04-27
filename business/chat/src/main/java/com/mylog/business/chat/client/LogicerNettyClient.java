package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.dylan.protocol.logicer.LogicerMessageBuilder;
import com.dylan.protocol.logicer.LogicerTalkWord;
import com.dylan.protocol.logicer.LogicerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylog.business.chat.config.NettyClientConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Objects;
import java.util.Stack;

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
            // Start the connection attempt.
            Channel ch = bootstrap.connect(serverAddr, port).sync().channel();
            ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(getUserName() + "@" + password));
            while (true){
                Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(getUserName(), null);
                if (Objects.nonNull(messageCenter) && !messageCenter.isEmpty()){
                    String nextLine = messageCenter.pop();
                    logger.debug("即将发送：" + nextLine);
                    if ("exit".equals(nextLine)){
                        logger.info("即将断开连接");
                        ch.writeAndFlush(LogicerMessageBuilder.buildExitMessage());
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e){
                            logger.error("failed to sleep, but its ok.");
                        }
                        ch.close();
                        break;
                    } else if ("connect".equals(nextLine)) {
                        connect(serverAddr, port);
                        break;
                    }
                    if (LogicerUtil.isLoginStr(nextLine)){
                        ch.writeAndFlush(LogicerMessageBuilder.buildLoginMessage(nextLine));
                    }else {
                        // 不是登录类型的消息 就默认使用talk子协议进行发送
                        // ch.writeAndFlush(LogicerMessageBuilder.buildMessage(nextLine));
                        LogicerTalkWord msg = new LogicerTalkWord();
                        msg.setType("0");
                        msg.setTo("duke");
                        msg.setMsg(nextLine);
                        ch.writeAndFlush(LogicerMessageBuilder.buildMessage(new ObjectMapper().writeValueAsString(msg)));
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
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
