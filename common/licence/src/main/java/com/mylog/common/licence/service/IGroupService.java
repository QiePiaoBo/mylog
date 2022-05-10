package com.mylog.common.licence.service;

import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IGroupService
 * @Description IGroupService
 * @Date 5/10/2022 4:06 PM
 */
public interface IGroupService {

    HttpResult getPagedGroup(MyPage myPage);
}
