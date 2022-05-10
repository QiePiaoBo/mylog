package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.GroupDTO;
import com.mylog.common.licence.service.IGroupService;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname GroupController
 * @Description GroupController
 * @Date 5/10/2022 4:31 PM
 */
@RestController
@RequestMapping("group")
public class GroupController {

    @Resource
    private IGroupService groupService;

    @GetMapping
    public HttpResult getPagedGroup(@Param("page") Integer page, @Param("limit") Integer limit){
        MyPage myPage = new MyPage(page, limit);
        return groupService.getPagedGroup(myPage);
    }

    @PostMapping
    public HttpResult createGroup(@RequestBody GroupDTO groupDTO){

        return groupService.createGroup(groupDTO);
    }

}


