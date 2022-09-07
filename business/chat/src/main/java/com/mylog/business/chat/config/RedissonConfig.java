package com.mylog.business.chat.config;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RedissonConfig
 * @Description RedissonConfig
 * @Date 9/7/2022 3:17 PM
 */
@Configuration
public class RedissonConfig {

    private static final MyLogger log = MyLoggerFactory.getLogger(RedissonConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;


    @Bean("redissonClient")
    public RedissonClient getRedisson(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }

}
