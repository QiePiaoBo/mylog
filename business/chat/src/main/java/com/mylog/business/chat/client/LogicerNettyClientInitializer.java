package com.mylog.business.chat.client;

import com.dylan.protocol.logicer.LogicerDecoder;
import com.dylan.protocol.logicer.LogicerEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Dylan
 * @Date : 2022/4/13 - 15:08
 */
public class LogicerNettyClientInitializer extends ChannelInitializer<SocketChannel> {

    private final String sessionId;

    public LogicerNettyClientInitializer(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(new LengthFieldBasedFrameDecoder(10240, 0, 4));
        pipeline.addLast(new LogicerEncoder());
        pipeline.addLast(new LogicerDecoder(true));

        // and then business logic.
        pipeline.addLast(new LogicerNettyClientHandler(getSessionId()));
    }

    public String getSessionId() {
        return sessionId;
    }
}
