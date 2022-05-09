package com.mylog.common.licence.service;

import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @author Dylan
 * @Description IAccessService
 * @Date : 2022/5/9 - 23:44
 */
public interface IAccessService {

    /**
     * 分页获取权限
     * @param myPage
     * @return
     */
    HttpResult getPagedAccess(MyPage myPage);

}
