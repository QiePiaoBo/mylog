package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Permission;
import com.mylog.common.licence.mapper.PermissionMapper;
import com.mylog.common.licence.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  权限服务实现类
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
