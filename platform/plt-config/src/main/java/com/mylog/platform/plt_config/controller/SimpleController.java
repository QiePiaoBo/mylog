package com.mylog.platform.plt_config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SimpleController {

    @Value("${user.name:null}")
    String userName;

    @Value("${user.age:25}")
    int age;

    @Value("${current.env:null}")
    String current;

    @GetMapping("/user")
    public String simple(){
        return "Hello Nacos Config!" + "Hello " + userName + " "
                + age + "!" + "current:" + current;
    }

}
