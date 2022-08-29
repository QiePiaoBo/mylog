package com.mylog.business.chat.config;

import com.dylan.mq.LogicerTalkMqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @Classname RabbitMQConfig
 * @Description RabbitMQConfig
 * @Date 6/17/2022 11:15 AM
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public AmqpTemplate amqpTemplate(){

        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        // 开启returncallback    yml 需要配置publisher-returns: true
        rabbitTemplate.setReturnCallback(((message,  replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        }));
        //开启消息确认  yml 需要配置   publisher-returns: true
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) ->{
            if (ack) {
                logger.info("消息发送到交换机成功,correlationId:{}", Objects.nonNull(correlationData) ? correlationData.getId() : "null");
            } else {
                logger.info("消息发送到交换机失败,原因:{}",cause);
            }
        } ));
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(LogicerTalkMqConstant.LOGICER_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue logicerQueueTalk(){
        return new Queue(LogicerTalkMqConstant.LOGICER_QUEUE_TALK, true);
    }

    @Bean
    public Binding logicerBindingKeyTalk(){
        return BindingBuilder.bind(logicerQueueTalk()).to(directExchange()).with(LogicerTalkMqConstant.LOGICER_ROUTING_KEY_TALK);
    }
}
