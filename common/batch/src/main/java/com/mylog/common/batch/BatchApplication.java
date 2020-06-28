package com.mylog.common.batch;

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
@SpringBootApplication
@EnableScheduling
@ImportResource(locations = "classpath:/batchJobs/**.xml")
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
