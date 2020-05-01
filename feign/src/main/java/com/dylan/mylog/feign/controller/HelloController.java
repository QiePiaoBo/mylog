package com.dylan.mylog.feign.controller;

import com.dylan.mylog.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hi")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }
}
