package com.mylog.business.chat.client;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Classname LogicerConnectionExecutor
 * @Description LogicerConnectionExecutor
 * @Date 4/27/2023 1:57 PM
 */
@Configuration
public class LogicerNettyClientExecutor {

    private final MyLogger logger = MyLoggerFactory.getLogger(LogicerNettyClientExecutor.class);

    @Bean("nettyClientExecutor")
    public ThreadPoolExecutor nettyClientExecutor(){
        return new ThreadPoolExecutor(
                // 核心线程数
                3,
                // 最大线程数
                5,
                // 空闲线程最大存活时间
                60L,
                // 空闲线程最大存活时间单位
                TimeUnit.SECONDS,
                // 等待队列及大小
                new ArrayBlockingQueue<>(100),
                // 创建新线程时使用的工厂
                Executors.defaultThreadFactory(),
                // 当线程池达到最大时的处理策略
//                new ThreadPoolExecutor.AbortPolicy()          // 抛出RejectedExecutionHandler异常
                new ThreadPoolExecutor.CallerRunsPolicy()       // 交由调用者的线程执行
//                new ThreadPoolExecutor.DiscardOldestPolicy()  // 丢掉最早未处理的任务
//                new ThreadPoolExecutor.DiscardPolicy()        // 丢掉新提交的任务
        );
    }

}
