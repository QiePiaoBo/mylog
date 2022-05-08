package com.mylog.common.licence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.common.licence.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname UserInfoMapper
 * @Description userInfoMapper
 * @Date 5/7/2022 3:56 PM
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据userId获取用户的相关信息
     * @param userId
     * @return
     */
    UserInfo selectUserInfoByUserId(@Param("userId") Integer userId);

}
