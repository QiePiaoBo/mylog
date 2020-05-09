package com.dylan.mylog.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

}
