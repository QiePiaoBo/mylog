package com.mylog.common.licence.controller;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.model.annos.AdminPermission;
import com.mylog.tools.model.model.result.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 * 用户行为中心
 */
@Slf4j
@RestController
@RequestMapping("act")
public class UserActController {
    private static final MyLogger logger = MyLoggerFactory.getLogger(UserActController.class);
    @Autowired
    IUserService userService;

    /**
     * 登入
     * @param userDTO
     * @return
     */
    @PostMapping("login")
    public HttpResult login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /**
     * 登出
     * @return
     */
    @AdminPermission
    @GetMapping("logout")
    public HttpResult logout(){
        return userService.logout();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @AdminPermission
    @GetMapping("who")
    public HttpResult who(){
        logger.info("who is logging");
        return userService.getCurrentUser();
    }

}
