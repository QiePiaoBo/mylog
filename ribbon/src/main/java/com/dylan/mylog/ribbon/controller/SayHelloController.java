package com.dylan.mylog.ribbon.controller;

import com.dylan.mylog.ribbon.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @Autowired
    SayHelloService sayHelloService;

    @GetMapping(value = "/hello")
    public String sayHello(String name){
        return sayHelloService.sayHello() + " " + name;
    }


}
