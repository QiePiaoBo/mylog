package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.permission.permissions.AdminPermission;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.session.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

/**
 * blog接口集，
 * @author Dylan
 */
@RestController
@RequestMapping("user")
public class LicController {

    @Autowired
    UserService userService;

    /**
     * 获取当前登录用户
     * @return
     */
    @RequestMapping("getUser")
    public Result getUser(){
        Person p = userService.getUser();
        return new Result().put("status", 200).put("msg", "ok").put("data", p);
    }


}
