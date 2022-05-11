package com.mylog.common.licence.service;

import com.mylog.tools.model.model.result.HttpResult;

/**
 * @Classname IUserAccessService
 * @Description IUserAccessService
 * @Date 5/11/2022 2:28 PM
 */
public interface IUserAccessService {

    /**
     * 根据用户id获取其所拥有的权限
     * @param id
     * @return
     */
    HttpResult getAccesses4User(Integer id);

}
