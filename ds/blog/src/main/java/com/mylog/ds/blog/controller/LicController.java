package com.mylog.ds.blog.controller;

import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.session.UserContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog")
public class LicController {
    @RequestMapping("getUser")
    public Result getUser(){
        return new Result().put("status", 200).put("msg", "ok").put("data", new UserContext().getCurrentUser());
    }
}
