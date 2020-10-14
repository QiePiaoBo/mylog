package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.annos.AdminPermission;
import com.mylog.tools.entitys.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan
 * 用户行为中心
 */
@Slf4j
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
    public Result login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /**
     * 登出
     * @return
     */
    @AdminPermission
    @RequestMapping("logout")
    public Result logout(){
        return userService.logout();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @AdminPermission
    @RequestMapping("who")
    public Result who(){
        log.info("who is logging");
        return userService.getCurrentUser();
    }

}
