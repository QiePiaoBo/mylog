package com.mylog.common.licence.controller;

import com.mylog.common.licence.entity.Result;
import com.mylog.common.licence.model.DTO.UserDTO;
import com.mylog.common.licence.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licence")
public class UserActController {

    @Autowired
    IUserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

}
