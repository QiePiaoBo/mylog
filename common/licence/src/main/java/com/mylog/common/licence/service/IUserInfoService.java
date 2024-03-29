package com.mylog.common.licence.service;

import com.mylog.common.licence.model.dto.UserInfoDTO;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IUserInfoService
 * @Description IUserInfoService
 * @Date 5/7/2022 4:41 PM
 */
public interface IUserInfoService {

    /**
     * 根据userId获取改用户的详细信息
     * @param userId
     * @return
     */
    HttpResult selectUserInfoByUserId(Integer userId);

    HttpResult getPagedUserInfo(Integer page, Integer limit);

    /**
     * 更新userInfo的信息
     */
    HttpResult updateUserInfo(UserInfoDTO userInfoDTO);

}
