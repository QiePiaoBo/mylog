package com.mylog.common.licence.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;


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
    HttpResult selectUserList(MyPage page);

    /**
     * 获取单个用户
     * @param userDTO
     * @return
     */
    HttpResult selectOne(UserDTO userDTO);

    /**
     * 添加一个用户
     * @param userDTO
     * @return
     */
    HttpResult addUser(UserDTO userDTO);

    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    HttpResult deleteOne(UserDTO userDTO);

    /**
     * 修改一个用户
     * @param userDTO
     * @return
     */
    HttpResult exchange(UserDTO userDTO);

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    HttpResult login(UserDTO userDTO);

    /**
     * 用户登出
     * @return
     */
    HttpResult logout();

    /**
     * 获取客户端当前的用户
     * @return
     */
    HttpResult getCurrentUser();
}
