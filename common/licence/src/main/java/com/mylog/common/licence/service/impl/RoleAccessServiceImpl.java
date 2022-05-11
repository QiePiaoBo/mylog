package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Access;
import com.mylog.common.licence.mapper.RoleAccessMapper;
import com.mylog.common.licence.service.IAccessService;
import com.mylog.common.licence.service.IRoleAccessService;
import com.mylog.common.licence.service.IRoleService;
import com.mylog.tools.model.model.result.HttpResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname RoleAccessServiceImpl
 * @Description RoleAccessServiceImpl
 * @Date 5/11/2022 9:39 AM
 */
@Service
public class RoleAccessServiceImpl implements IRoleAccessService {

    @Resource
    private RoleAccessMapper roleAccessMapper;

    @Resource
    private IRoleService roleService;

    @Resource
    private IAccessService accessService;


    /**
     * 根据角色id获取权限列表
     *
     * @param rId
     * @return
     */
    @Override
    public HttpResult getAccesses4Role(Integer rId) {
        List<Integer> accessIds = roleAccessMapper.getAccessList4Role(rId);
        return accessService.selectAccessListByIds(accessIds);
    }
}