package com.mylog.common.licence.service;

import com.mylog.common.licence.model.dto.GroupDTO;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IGroupService
 * @Description IGroupService
 * @Date 5/10/2022 4:06 PM
 */
public interface IGroupService {

    /**
     * 分页获取用户组
     * @param myPage
     * @return
     */
    HttpResult getPagedGroup(MyPage myPage);

    /**
     * 创建用户组
     * @param groupDTO
     * @return
     */
    HttpResult createGroup(GroupDTO groupDTO);
}
