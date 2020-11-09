package com.mylog.common.licence;

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
    public static void main(String[] args) {
        SpringApplication.run(LicenceApplication.class, args);
    }
}
