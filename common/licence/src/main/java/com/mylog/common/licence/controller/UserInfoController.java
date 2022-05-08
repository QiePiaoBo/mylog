package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.UserInfoDTO;
import com.mylog.common.licence.service.IUserInfoService;
import com.mylog.tools.model.model.result.DataResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Classname UserInfoController
 * @Description UserInfoController
 * @Date 5/7/2022 4:45 PM
 */
@Slf4j
@RestController
@RequestMapping("userinfo")
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

    @GetMapping
    public DataResult getPagedUserInfo(@Param("page") Integer page, @Param("limit") Integer limit){
        return userInfoService.getPagedUserInfo(page, limit);
    }

    /**
     * 根据userId查询该用户的信息
     * @param userInfoDTO
     * @return
     */
    @PatchMapping
    public DataResult updateUserInfo(@RequestBody UserInfoDTO userInfoDTO){

        int nullOfId = 0;
        nullOfId = Objects.isNull(userInfoDTO.getIdType()) ? nullOfId : nullOfId + 1;
        nullOfId = Objects.isNull(userInfoDTO.getIdCode()) ? nullOfId : nullOfId + 1;
        if (nullOfId == 1){
            log.error("Error userInfoDTO:{}, Id type must with id code.", userInfoDTO);
            return DataResult.fail().build();
        }
        return userInfoService.updateUserInfo(userInfoDTO);
    }

}
