package com.mylog.demos.ribbon.controller;

import com.mylog.demos.ribbon.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 */
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
