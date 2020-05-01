package com.dylan.mylog.ribbon.service;

import org.springframework.stereotype.Service;

@Service
public class SayHelloService {

    public String sayHello(){
        return "Hello, nice to meet you";
    }

}
