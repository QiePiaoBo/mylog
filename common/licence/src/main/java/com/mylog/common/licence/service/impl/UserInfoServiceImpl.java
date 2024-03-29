package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.UserInfo;
import com.mylog.common.licence.mapper.UserInfoMapper;
import com.mylog.common.licence.model.dto.UserInfoDTO;
import com.mylog.common.licence.model.vo.UserInfoVO;
import com.mylog.common.licence.service.IUserInfoService;
import com.mylog.common.licence.transformer.UserInfoTransformer;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Classname UserInfoServiceImpl
 * @Description UserInfoServiceImpl
 * @Date 5/7/2022 4:43 PM
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoMapper mapper;

    /**
     * 根据userId获取该用户对应的信息
     * @param userId
     * @return
     */
    @Override
    public HttpResult selectUserInfoByUserId(Integer userId) {
        UserInfo userInfo = mapper.selectUserInfoByUserId(userId);
        return DataResult.success().data(UserInfoTransformer.userInfo2UserInfoVO(userInfo)).build();
    }

    /**
     * 分页获取用户信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public HttpResult getPagedUserInfo(Integer page, Integer limit) {
        MyPage myPage = new MyPage(page, limit);
        List<UserInfo> userInfos = mapper.getPagedUserInfo(myPage);
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        Safes.of(userInfos).forEach(m ->{
            userInfoVOS.add(UserInfoTransformer.userInfo2UserInfoVO(m));
        });
        log.info("MyPage: {}, userInfoVos: {}", myPage, userInfoVOS);
        return PageDataResult
                .success()
                .page(page)
                .size(limit)
                .data(userInfoVOS)
                .total(mapper.selectUserInfoTotal())
                .build();
    }

    /**
     * 根据id更新用户信息
     * @param userInfoDTO
     * @return
     */
    @Override
    public HttpResult updateUserInfo(UserInfoDTO userInfoDTO) {
        if (Objects.isNull(userInfoDTO.getId())){
            return DataResult.fail().build();
        }
        UserInfo userInfo = UserInfoTransformer.userInfoDTO2UserInfo(userInfoDTO);
        log.info("UserInfo to insert : {}", userInfo);
        int updated = mapper.updateById(userInfo);
        if (updated <= 0){
            return DataResult.fail(Status.UPDATE_ERROR.getStatus(), Message.UPDATE_ERROR.getMsg()).build();
        }
        UserInfoVO userInfoVO = UserInfoTransformer.userInfo2UserInfoVO(mapper.selectById(userInfo.getId()));
        return DataResult.success().data(userInfoVO).build();
    }

}
