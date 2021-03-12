package com.mylog.common.licence.service;

import com.mylog.common.licence.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.entitys.entitys.result.DataResult;
import com.mylog.entitys.entitys.page.MyPage;


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
    DataResult selectUserList(MyPage page);

    /**
     * 获取单个用户
     * @param userDTO
     * @return
     */
    DataResult selectOne(UserDTO userDTO);

    /**
     * 添加一个用户
     * @param userDTO
     * @return
     */
    DataResult addUser(UserDTO userDTO);

    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    DataResult deleteOne(UserDTO userDTO);

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    DataResult exchange(UserDTO userDTO);

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    DataResult login(UserDTO userDTO);

    /**
     * 用户登出
     * @return
     */
    DataResult logout();

    /**
     * 获取客户端当前的用户
     * @return
     */
    DataResult getCurrentUser();
}
