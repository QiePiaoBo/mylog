package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.service.IUserService;
import com.mylog.entitys.annos.AdminPermission;
import com.mylog.entitys.entitys.result.DataResult;
import com.mylog.entitys.entitys.page.MyPage;
import com.mylog.entitys.entitys.info.Message;
import com.mylog.entitys.entitys.info.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * @author Dylan
 * @since 2020-05-24
 * 用户管理中心
 */
@RestController
@RequestMapping("manage")
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
    public DataResult getUsers(Integer page, Integer limit){
        if (page == null || limit == null){
            return new DataResult.Builder(Status.PARAM_NEED.getStatus(), Message.PARAM_NEED.getMsg()).data(new ArrayList<>()).build();
        }
        MyPage myPage = new MyPage(page, limit);
        return userService.selectUserList(myPage);
    }

    /**
     * 获取一个用户
     * @param userDTO
     * @return
     */
    @AdminPermission
    @RequestMapping("one")
    public DataResult getUser(@RequestBody UserDTO userDTO){
        return userService.selectOne(userDTO);
    }

    /**
     * 添加一个用户
     * @param userDTO
     * @return
     */
    @RequestMapping("add")
    public DataResult addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    @AdminPermission(userType = 1)
    @RequestMapping("delete")
    public DataResult deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteOne(userDTO);
    }

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    @AdminPermission
    @RequestMapping("exchange")
    public DataResult exchange(@RequestBody UserDTO userDTO){
        return userService.exchange(userDTO);
    }

}
