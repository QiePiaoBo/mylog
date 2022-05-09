package com.mylog.common.licence.service;

import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IRoleService
 * @Description IRoleService
 * @Date 5/9/2022 11:25 AM
 */
public interface IRoleService {

    HttpResult selectRoleList(MyPage myPage);
}
