package com.mylog.ds.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Dylan
 */
@SpringBootApplication(scanBasePackages = {"com.mylog.ds.blog", "com.mylog.entitys.utils-server"})
@EnableRedisHttpSession
@EnableDiscoveryClient
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
