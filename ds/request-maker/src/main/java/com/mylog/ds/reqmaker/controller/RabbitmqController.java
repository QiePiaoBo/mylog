package com.mylog.ds.reqmaker.controller;


import com.mylog.ds.reqmaker.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitmqController {

    @Autowired
    RabbitmqService rabbitmqService;


    @RequestMapping("/sendWork")
    public Object sendWork(){
        rabbitmqService.sendWork();
        return "发送成功...";
    }

    @RequestMapping("/sendPublish")
    public Object sendPublish(){
        rabbitmqService.sendPublish();
        return "发送成功。。。";
    }

}
