package com.dylan.mylog.ribbon.controller;

import com.dylan.mylog.ribbon.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @Autowired
    SayHelloService sayHelloService;

    @GetMapping(value = "/hello")
    public String sayHello(String name){
        return sayHelloService.sayHello() + " " + name + ". I'm ribbon01";
    }

    @GetMapping(value = "/gatewayTest")
    public String gatewayTest(String name){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ribbon Give you routeAll";
    }

    @RequestMapping("/age")
    public String age(String age){
        return "I am " + age + " years old this year. aaa";
    }


}
