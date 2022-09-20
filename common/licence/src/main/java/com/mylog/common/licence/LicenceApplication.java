package com.mylog.common.licence;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Dylan
 * @Description 用户及权限管理
 */
@SpringBootApplication(scanBasePackages = {"com.mylog.common.licence", "com.mylog.tools.utils", "com.dylan"})
@EnableRedisHttpSession
@EnableDiscoveryClient
public class LicenceApplication {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LicenceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicenceApplication.class, args);
        logger.info("LicenceApplication started.");
    }
}
