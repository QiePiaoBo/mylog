package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.session.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 */
@RestController
@RequestMapping("blog")
public class LicController {
    @Autowired
    UserService userService;


    @RequestMapping("getUser")
    public Result getUser(){
        Person p = userService.getUser();
        return new Result().put("status", 200).put("msg", "ok").put("data", p);
    }
}
