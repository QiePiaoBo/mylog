package com.mylog.common.licence.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.common.licence.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUserList(Page<User> page);
}
