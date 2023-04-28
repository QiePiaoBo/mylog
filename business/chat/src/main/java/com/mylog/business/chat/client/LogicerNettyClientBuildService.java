package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.config.NettyClientConstant;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("nettyClientExecutor")
    private ThreadPoolExecutor nettyClientExecutor;

    private final MyLogger logger = MyLoggerFactory.getLogger(LogicerNettyClientBuildService.class);

    /**
     * 创建并启动netty客户端
     * @param userName
     * @param password
     */
    public void startConnection(String userName, String password){
        // 创建netty客户端
        LogicerNettyClient logicerNettyClient = getLogicerNettyClient(userName, password);
        logger.info("thread condition: corePoolSize={}, poolSize={}, activeCount={}, completedTaskCount={}",
                nettyClientExecutor.getCorePoolSize(),
                nettyClientExecutor.getPoolSize(),
                nettyClientExecutor.getActiveCount(),
                nettyClientExecutor.getCompletedTaskCount());
        // 启动netty客户端
        nettyClientExecutor.execute(() -> {
            try {
                logicerNettyClient.connect("localhost", 8001);
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
        LogicerNettyClient logicerNettyClient = NettyClientConstant.USER_NETTY_CLIENT_CENTER.getOrDefault(userName, null);
        if (Objects.nonNull(logicerNettyClient)){
            return logicerNettyClient;
        }
        // 如果user-client中没有 就说明没存过或者没有正在在线的 就创建并为用户注册
        logicerNettyClient = new LogicerNettyClient(userName, password);
        //  为userName注册消息中心
        Stack<String> messageCenter = NettyClientConstant.USER_MESSAGE_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(messageCenter)){
            messageCenter = new Stack<>();
            NettyClientConstant.USER_MESSAGE_CENTER.put(userName, messageCenter);
        }
        // 为userName注册EventLoopGroup
        LogicerNettyClient savedClient = NettyClientConstant.USER_NETTY_CLIENT_CENTER.getOrDefault(userName, null);
        if (Objects.isNull(savedClient)){
            savedClient = logicerNettyClient;
            NettyClientConstant.USER_NETTY_CLIENT_CENTER.put(userName, savedClient);
        }
        return logicerNettyClient;
    }

}
