package com.mylog.ds.reqhandler.config;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RabbitmqConfig {

    @Bean
    public Queue queueWork1(){
        return new Queue("queue_work");
    }

}
