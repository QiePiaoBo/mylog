package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Access;
import com.mylog.common.licence.mapper.RoleAccessMapper;
import com.mylog.common.licence.mapper.UserMapper;
import com.mylog.common.licence.model.vo.AccessVO;
import com.mylog.common.licence.service.IUserAccessService;
import com.mylog.common.licence.transformer.AccessTransformer;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname UserAccessServiceImpl
 * @Description UserAccessServiceImpl
 * @Date 5/11/2022 2:29 PM
 */
@Service
public class UserAccessServiceImpl implements IUserAccessService {

    @Resource
    private RoleAccessMapper roleAccessMapper;

    @Resource
    private UserMapper userMapper;


    /**
     * 根据用户id获取其所拥有的权限
     *
     * @param id
     * @return
     */
    @Override
    public HttpResult getAccesses4User(Integer id) {
        // 两种途径获取角色列表
        List<Integer> roleIds = userMapper.getAllRole4User(id);
        // 根据roleIds获取accesses
        List<Access> accesses = roleAccessMapper.getAccesses4RoleIds(roleIds);
        // 根据accesses获取accessVOS
        List<AccessVO> accessVOS = Safes.of(accesses)
                .stream()
                .map(AccessTransformer::access2AccessVO)
                .collect(Collectors.toList());
        return DataResult
                .success()
                .data(accessVOS)
                .build();
    }

    /**
     * id为id的用户是否拥有url的权限
     *
     * @param id
     * @param url
     * @return
     */
    @Override
    public HttpResult hasPermission(Integer id, String url) {
        // 两种途径获取角色列表
        List<Integer> roleIds = userMapper.getAllRole4User(id);
        // 根据roleIds获取accesses
        List<Access> accesses = roleAccessMapper.getAccesses4RoleIds(roleIds);
        for (Access a : Safes.of(accesses)){
            if (Objects.equals(url, a.getAccessUri())){
                return DataResult.success().data(true).build();
            }
        }
        return DataResult.fail().build();
    }
}