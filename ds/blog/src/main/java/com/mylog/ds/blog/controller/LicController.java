package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.vo.UserVO;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.annos.AdminPermission;
import com.mylog.tools.entitys.entity.Result;
import com.mylog.tools.entitys.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @AdminPermission
    @RequestMapping("getUser")
    public Result getUser(){
        PersonVo userVO = userService.getUser();
        return new Result().put("status", 200).put("msg", "ok").put("data", userVO);
    }


}
