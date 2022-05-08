package com.mylog.common.licence.service;

import com.mylog.common.licence.model.dto.UserInfoDTO;
import com.mylog.tools.model.model.result.DataResult;

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
    DataResult selectUserInfoByUserId(Integer userId);

    DataResult getPagedUserInfo(Integer page, Integer limit);

    /**
     * 更新userInfo的信息
     */
    DataResult updateUserInfo(UserInfoDTO userInfoDTO);

}
