package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.service.IFileService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.session.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * blog接口集，
 * @author Dylan
 */
@RestController
@RequestMapping("blog")
public class LicController {

    @Autowired
    UserService userService;
    @Autowired
    IFileService fileService;

    /**
     * 获取当前登录用户
     * @return
     */
    @RequestMapping("getUser")
    public Result getUser(){
        Person p = userService.getUser();
        return new Result().put("status", 200).put("msg", "ok").put("data", p);
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping("uploadFile")
    public Result uploadFile(@RequestParam(value = "file", required = false) MultipartFile file){
        if (file==null){
            return new Result().put("status",444).put("msg","File is null.");
        }
        return fileService.uploadFile(file);
    }


}
