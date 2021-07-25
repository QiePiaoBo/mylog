package com.mylog.common.licence.controller;


import com.mylog.common.licence.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    IGroupService groupService;

    @RequestMapping("testex")
    public String checkExceptionHandler(){
        return groupService.testex();
    }
}
