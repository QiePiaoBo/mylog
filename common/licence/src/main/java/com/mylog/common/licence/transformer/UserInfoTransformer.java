package com.mylog.common.licence.transformer;

import com.mylog.common.licence.entity.UserInfo;
import com.mylog.common.licence.model.vo.UserInfoVO;
import com.mylog.tools.utils.utils.Safes;

/**
 * @Classname UserInfoTransformer
 * @Description UserInfoTransformer
 * @Date 5/7/2022 5:12 PM
 */
public class UserInfoTransformer {

    /**
     * userInfo -> userInfoVO
     * @param userInfo
     * @return
     */
    public static UserInfoVO userInfo2UserInfoVO(UserInfo userInfo){
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(userInfo.getUserId());
        userInfoVO.setGender(userInfo.getGender());
        userInfoVO.setMail(Safes.of(userInfo.getMail()));
        userInfoVO.setRealName(Safes.of(userInfo.getRealName()));
        userInfoVO.setWechatCode(Safes.of(userInfo.getWechatCode()));
        return userInfoVO;
    }

}
