package com.mylog.common.batch.controller;

import com.mylog.common.batch.service.IMailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 * @Date : Created in 12:21 2020/6/23
 * @Description : java类
 */
@RestController
@RequestMapping("mail")
public class MailSendController {

    @Autowired
    IMailSendService mailSendService;

    @RequestMapping("admin")
    public String adminMail(){
        return mailSendService.adminSendMail();
    }
    @RequestMapping("user")
    public String userMail(){
        return mailSendService.userSendMail();
    }
}