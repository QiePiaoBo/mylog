package com.dylan.mylog.config.controller;

import com.dylan.mylog.config.configuration.TestPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class gettingConfigTest {

    @Autowired
    TestPropertyService testPropertyService;

    @RequestMapping("/user")
    public String getUserInfo(){
        return "Hello, " + testPropertyService.getUserName() + ", is your age is " + testPropertyService.getUserAge() + " ?";
    }

}
