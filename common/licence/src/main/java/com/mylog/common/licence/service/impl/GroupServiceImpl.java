package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Group;
import com.mylog.common.licence.mapper.GroupMapper;
import com.mylog.common.licence.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  组别服务实现类
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

}