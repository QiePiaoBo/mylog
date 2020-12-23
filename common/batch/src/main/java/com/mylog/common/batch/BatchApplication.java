package com.mylog.common.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 批量服务
 *
 * @author Dylan
 */
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.mylog.common.batch", "com.mylog.tools.utils"})
@EnableScheduling
@ImportResource(locations = "classpath:/batchJobs/**.xml")
public class BatchApplication {

    private static final Logger logger = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
        logger.info("BatchApplication started.");
    }
}
