package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.RoleDTO;
import com.mylog.common.licence.service.IRoleAccessService;
import com.mylog.common.licence.service.IRoleService;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private IRoleAccessService roleAccessService;

    /**
     * 分页获取角色
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public HttpResult getRoleList(@Param("page") Integer page, @Param("limit") Integer limit){
        MyPage myPage = new MyPage(page, limit);
        return roleService.selectRoleList(myPage);
    }

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    @PostMapping
    public HttpResult create(@RequestBody RoleDTO roleDTO){
        return roleService.create(roleDTO);
    }

    /**
     * 根据id获取角色
     */
    @GetMapping("/{id:\\d+}")
    public HttpResult getById(@PathVariable Integer id){
        return roleService.getById(id);
    }

    /**
     * 根据id删除角色
     */
    @DeleteMapping("/{id:\\d+}")
    public HttpResult deleteById(@PathVariable Integer id){
        return roleService.deleteById(id);
    }

    /**
     * 根据id修改角色
     */
    @PatchMapping
    public HttpResult updateById(@RequestBody RoleDTO roleDTO){
        return roleService.updateById(roleDTO);
    }

    @GetMapping("get-accesses")
    public HttpResult getAccesses4Role(@Param("id") Integer id){
        return roleAccessService.getAccesses4Role(id);
    }

}
