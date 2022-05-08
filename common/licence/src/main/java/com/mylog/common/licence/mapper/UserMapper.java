package com.mylog.common.licence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.common.licence.entity.User;
import com.mylog.tools.model.model.page.MyPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户列表 mapper
     * @param page
     * @return
     */
    List<User> selectUserList(@Param("myPage") MyPage myPage);

    /**
     * 获取user总数量
     * @return
     */
    Long selectUserTotal();
}
