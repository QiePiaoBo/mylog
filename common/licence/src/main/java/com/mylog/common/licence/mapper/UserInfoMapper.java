package com.mylog.common.licence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.common.licence.entity.UserInfo;
import com.mylog.tools.model.model.page.MyPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 分页获取userInfo
     * @param myPage
     * @return
     */
    List<UserInfo> getPagedUserInfo(@Param("myPage")MyPage myPage);

    /**
     * 获取user总数量
     * @return
     */
    Long selectUserInfoTotal();

}
