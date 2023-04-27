package com.mylog.business.chat.client;

import com.mylog.business.chat.config.NettyClientConstant;
import io.netty.channel.EventLoopGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname ClientBuilder
 * @Description ClientBuilder
 * @Date 4/27/2023 2:03 PM
 */
@Service
public class LogicerNettyClientBuildService {

    @Resource
    private ThreadPoolExecutor nettyClientExecutor;

    /**
     * 创建并启动netty客户端
     * @param userName
     * @param password
     */
    public void startConnection(String userName, String password){
        // 创建netty客户端
        LogicerNettyClient logicerNettyClient = getLogicerNettyClient(userName, password);
        // 启动netty客户端
        nettyClientExecutor.execute(() -> {
            try {
                logicerNettyClient.connect("logicer.top", 8001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建netty客户端
     * @param userName
     * @param password
     * @return
     */
    private LogicerNettyClient getLogicerNettyClient(String userName, String password) {
        LogicerNettyClient logicerNettyClient = new LogicerNettyClient(userName, password);
        //  为userName注册消息中心
        Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(messageCenter)){
            messageCenter = new Stack<>();
            NettyClientConstant.USER_MESSAGE_CENTER.put(userName, messageCenter);
        }
        // 为userName注册EventLoopGroup
        EventLoopGroup eventLoopGroup = NettyClientConstant.USER_GROUP_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(eventLoopGroup)){
            eventLoopGroup = logicerNettyClient.getGroup();
            NettyClientConstant.USER_GROUP_CENTER.put(userName, eventLoopGroup);
        }
        return logicerNettyClient;
    }

}
