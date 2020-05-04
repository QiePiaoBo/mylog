package com.dylan.mylog.feign.controller;

import com.dylan.mylog.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hi")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }

    @RequestMapping("/hello")
    public String sayHello1(String name){
        return "Hello, " + name + ". I'm Feign.";
    }

    @RequestMapping("/age")
    public String age(String age){
        return "I am " + age + " years old this year. aaa";
    }
    @GetMapping(value = "/routeAll")
    public String routeAll(String name){
        return "Feign Give you routeAll";
    }

}
