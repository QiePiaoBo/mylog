package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.permission.permissions.AdminPermission;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.lic.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan
 */
@RestController
@RequestMapping("/licence/act/")
public class UserActController {

    @Autowired
    IUserService userService;

    @PostMapping("login")
    public Result login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @RequestMapping("logout")
    public Result logout(){
        return userService.logout();
    }

    @AdminPermission
    @RequestMapping("who")
    public Result who(){
        return userService.getCurrentUser();
    }

}
