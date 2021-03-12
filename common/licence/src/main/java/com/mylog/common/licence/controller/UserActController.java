package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.service.IUserService;
import com.mylog.entitys.annos.AdminPermission;
import com.mylog.entitys.entitys.result.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan
 * 用户行为中心
 */
@RestController
@RequestMapping("act")
public class UserActController {
    private static final Logger logger = LoggerFactory.getLogger(UserActController.class);
    @Autowired
    IUserService userService;

    /**
     * 登入
     * @param userDTO
     * @return
     */
    @PostMapping("login")
    public DataResult login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /**
     * 登出
     * @return
     */
    @AdminPermission
    @RequestMapping("logout")
    public DataResult logout(){
        return userService.logout();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @AdminPermission
    @RequestMapping("who")
    public DataResult who(){
        logger.info("who is logging");
        return userService.getCurrentUser();
    }

}
