package com.mylog.ds.reqmaker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RabbitmqConfig {

    @Bean
    public Queue queueWork1(){
        return new Queue("queue_work");
    }

    @Bean
    public Queue queueFanout1(){
        return new Queue("queue_fanout1");
    }

    @Bean
    public Queue queueFanout2(){
        return new Queue("queue_fanout2");
    }

    @Bean
    public FanoutExchange exchangeFanout(){
        return new FanoutExchange("exchange_fanout");
    }

    @Bean
    public Binding bingingExchange1(){
        return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
    }

    @Bean
    public Binding bindingExchange2(){
        return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
    }


}
