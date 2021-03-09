package com.mylog.common.licence.service;

import com.mylog.common.licence.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.entitys.entitys.page.MyPage;
import com.mylog.entitys.entitys.entity.Result;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户列表
     * @param page
     * @return
     */
    Result selectUserList(MyPage page);

    /**
     * 获取单个用户
     * @param userDTO
     * @return
     */
    Result selectOne(UserDTO userDTO);

    /**
     * 添加一个用户
     * @param userDTO
     * @return
     */
    Result addUser(UserDTO userDTO);

    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    Result deleteOne(UserDTO userDTO);

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    Result exchange(UserDTO userDTO);

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    Result login(UserDTO userDTO);

    /**
     * 用户登出
     * @return
     */
    Result logout();

    /**
     * 获取客户端当前的用户
     * @return
     */
    Result getCurrentUser();
}
