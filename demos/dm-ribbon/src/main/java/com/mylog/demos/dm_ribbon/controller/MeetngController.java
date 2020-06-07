package com.mylog.demos.dm_ribbon.controller;

import com.mylog.demos.dm_ribbon.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ribbon")
public class MeetngController {

    @Autowired
    MeetingService meetingService;


    @RequestMapping("/who")
    public String whoIsThere(){
        return meetingService.getPerson();
    }
}
