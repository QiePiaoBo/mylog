package com.mylog.business.blog;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Dylan
 */
@SpringBootApplication(scanBasePackages = {"com.mylog.business.blog", "com.mylog.tools.utils"})
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableCaching
public class BlogApplication {

    private static final MyLogger logger = MyLoggerFactory.getLogger(BlogApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        logger.info("BlogApplication started.");
    }
}
