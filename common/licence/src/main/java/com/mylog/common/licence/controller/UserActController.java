package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.permission.permissions.AdminPermission;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.utils.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan
 */
@Slf4j
@RestController
@RequestMapping("act")
public class UserActController {

    @Autowired
    IUserService userService;

    @PostMapping("login")
    public Result login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @AdminPermission
    @RequestMapping("logout")
    public Result logout(){
        return userService.logout();
    }

    @AdminPermission
    @RequestMapping("who")
    public Result who(){
        log.info("who is logging");
        return userService.getCurrentUser();
    }

}
