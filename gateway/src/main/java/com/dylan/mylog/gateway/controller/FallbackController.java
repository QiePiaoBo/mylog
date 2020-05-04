package com.dylan.mylog.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String fallback(){
        return "I'm Spring Cloud Gateway Fallback";
    }
}
