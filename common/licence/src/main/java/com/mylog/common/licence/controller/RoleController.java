package com.mylog.common.licence.controller;

import com.mylog.common.licence.service.IRoleService;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname RoleController
 * @Description RoleController
 * @Date 5/9/2022 11:37 AM
 */
@RequestMapping("role")
@RestController
public class RoleController {

    @Resource
    private IRoleService roleService;

    @GetMapping
    public HttpResult getRoleList(@Param("page") Integer page, @Param("limit") Integer limit){
        MyPage myPage = new MyPage(page, limit);
        return roleService.selectRoleList(myPage);
    }


}
