package com.mylog.common.licence.service;

import com.mylog.common.licence.model.dto.RoleDTO;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IRoleService
 * @Description IRoleService
 * @Date 5/9/2022 11:25 AM
 */
public interface IRoleService {

    /**
     * 分页查询角色列表
     * @param myPage
     * @return
     */
    HttpResult selectRoleList(MyPage myPage);

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    HttpResult create(RoleDTO roleDTO);

}
