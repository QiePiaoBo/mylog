package com.mylog.common.licence.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.model.DTO.UserDTO;
import com.mylog.common.licence.permission.permissions.AdminPermission;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.lic.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 获取所有用户
     * @param page
     * @param limit
     * @return
     */
    @AdminPermission(userType = 1)
    @RequestMapping("all")
    public Result getUsers(Integer page, Integer limit){
        if (page == null || limit == null){
            return new Result().put("status","1110").put("msg","获取失败").put("data", new ArrayList<>());
        }
        Page<User> users = new Page<>(page, limit);
        return userService.selectUserList(users);
    }

    /**
     * 获取一个用户
     * @param userDTO
     * @return
     */
    @RequestMapping("one")
    public Result getUser(@RequestBody UserDTO userDTO){
        return userService.selectOne(userDTO);
    }

    @RequestMapping("add")
    public Result addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    @RequestMapping("delete")
    public Result deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteOne(userDTO);
    }

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    @AdminPermission(userType = 2)
    @RequestMapping("exchange")
    public Result exchange(@RequestBody UserDTO userDTO){
        return userService.exchange(userDTO);
    }

}
