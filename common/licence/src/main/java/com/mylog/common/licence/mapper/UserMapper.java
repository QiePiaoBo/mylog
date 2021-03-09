package com.mylog.common.licence.mapper;

import com.mylog.common.licence.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.entitys.entitys.page.MyPage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 获取用户列表 mapper
     * @param page
     * @return
     */
    List<User> selectUserList(MyPage page);
}
