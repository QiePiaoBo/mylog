package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.service.IUserRoleService;
import com.mylog.common.licence.service.IUserService;
import com.mylog.tools.model.annos.AdminPermission;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Dylan
 * @since 2020-05-24
 * 用户管理中心
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IUserRoleService userRoleService;

    /**
     * 获取所有用户
     * @param page
     * @param limit
     * @return
     */
    @AdminPermission(userType = 1)
    @GetMapping
    public HttpResult getUsers(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        if (page == null || limit == null){
            return DataResult.getBuilder(Status.PARAM_NEED.getStatus(), Message.PARAM_NEED.getMsg()).data(new ArrayList<>()).build();
        }
        MyPage myPage = new MyPage(page, limit);
        return userService.selectUserList(myPage);
    }

    /**
     * 获取一个用户
     * @param id
     * @return
     */
    @AdminPermission
    @GetMapping("/{id:\\d+}")
    public HttpResult getUserById(@PathVariable Integer id){
        return userService.selectOne(UserDTO.builder().id(id).build());
    }

    /**
     * 添加一个用户
     * @param userDTO
     * @return
     */
    @PostMapping
    public HttpResult create(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @AdminPermission(userType = 1)
    @DeleteMapping("/{id:\\d+}")
    public HttpResult deleteUser(@PathVariable Integer id){
        return userService.deleteOne(UserDTO.builder().id(id).build());
    }

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    @AdminPermission
    @PatchMapping
    public HttpResult exchange(@RequestBody UserDTO userDTO){
        return userService.exchange(userDTO);
    }

    /**
     * 根据userId获取该用户的角色列表
     * @param id
     * @return
     */
    @GetMapping("get-roles")
    public HttpResult getRoles4User(@Param("id") Integer id){
        return userRoleService.getRoleList4User(id);
    }
}
