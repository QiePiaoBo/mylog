package com.mylog.ds.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Dylan
 */
@SpringBootApplication(scanBasePackages = {"com.mylog.ds.blog", "com.mylog.tools.utils"})
@EnableRedisHttpSession
@EnableDiscoveryClient
public class BlogApplication {

    private static final Logger logger = LoggerFactory.getLogger(BlogApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        logger.info("BlogApplication started.");
    }
}
