package com.mylog.common.licence.service;

import com.mylog.common.licence.model.dto.AccessDTO;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;

import java.util.List;

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

    /**
     * 创建权限
     * @param accessDTO
     * @return
     */
    HttpResult createAccess(AccessDTO accessDTO);

    /**
     * 根据id获取权限
     * @param id
     * @return
     */
    HttpResult getById(Integer id);

    /**
     * 根据id删除权限
     * @param id
     * @return
     */
    HttpResult deleteById(Integer id);

    /**
     * 根据Id修改权限
     * @param accessDTO
     * @return
     */
    HttpResult updateById(AccessDTO accessDTO);

    /**
     * 根据id列表查询权限列表
     * @param ids
     * @return
     */
    HttpResult selectAccessListByIds(List<Integer> ids);
}
