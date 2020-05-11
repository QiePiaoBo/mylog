package com.mylog.demos.dm_ribbon.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class MeetingService {

    @Value("${yaml1.userName}")
    String userName;

    @Value("${yaml1.userAge}")
    String userAge;

    @Value("${yaml1.userId}")
    String userId;

    public String getPerson(){

        return "那是" + userName + ", 一个id为" + userId + "的" + userAge + "岁靓仔";
    }


}
