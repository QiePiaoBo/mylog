package com.mylog.common.licence.controller;

import com.mylog.common.licence.service.IUserInfoService;
import com.mylog.tools.model.model.result.DataResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname UserInfoController
 * @Description UserInfoController
 * @Date 5/7/2022 4:45 PM
 */
@RestController
@RequestMapping("user-info")
public class UserInfoController {

    @Resource
    private IUserInfoService userInfoService;

    /**
     * 根据userId查询该用户的信息
     * @param userId
     * @return
     */
    @GetMapping("get-by-userid")
    public DataResult getUserInfoByUserId(@Param("userId") Integer userId){
        return userInfoService.selectUserInfoByUserId(userId);
    }

}
