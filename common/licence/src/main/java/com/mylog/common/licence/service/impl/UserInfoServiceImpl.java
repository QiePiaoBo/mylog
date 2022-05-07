package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.UserInfo;
import com.mylog.common.licence.mapper.UserInfoMapper;
import com.mylog.common.licence.service.IUserInfoService;
import com.mylog.common.licence.transformer.UserInfoTransformer;
import com.mylog.tools.model.model.result.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname UserInfoServiceImpl
 * @Description UserInfoServiceImpl
 * @Date 5/7/2022 4:43 PM
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoMapper mapper;

    @Override
    public DataResult selectUserInfoByUserId(Integer userId) {
        UserInfo userInfo = mapper.selectUserInfoByUserId(userId);
        return DataResult.success().data(UserInfoTransformer.userInfo2UserInfoVO(userInfo)).build();
    }

}
