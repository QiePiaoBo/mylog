package com.mylog.common.licence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Dylan
 */
@SpringBootApplication(scanBasePackages = {"com.mylog.common.licence", "com.mylog.tools.utils"})
@EnableRedisHttpSession
@EnableDiscoveryClient
public class LicenceApplication {

    private static final Logger logger = LoggerFactory.getLogger(LicenceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicenceApplication.class, args);
        logger.info("LicenceApplication started.");
    }
}
