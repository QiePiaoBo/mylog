package com.mylog.business.blog.controller;

import com.mylog.business.blog.service.UserService;
import com.mylog.tools.model.annos.AdminPermission;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.vo.PersonVo;
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
    public HttpResult getUser(){
        PersonVo userVO = userService.getUser();
        return new HttpResult(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg(), userVO);
    }


}
