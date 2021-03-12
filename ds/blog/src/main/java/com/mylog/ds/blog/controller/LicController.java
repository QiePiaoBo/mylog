package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.service.UserService;
import com.mylog.entitys.annos.AdminPermission;
import com.mylog.entitys.entitys.info.Message;
import com.mylog.entitys.entitys.result.DataResult;
import com.mylog.entitys.entitys.info.Status;
import com.mylog.entitys.entitys.vo.PersonVo;
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
    public DataResult getUser(){
        PersonVo userVO = userService.getUser();
        return new DataResult.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg())
                .data(userVO)
                .build();
    }


}
