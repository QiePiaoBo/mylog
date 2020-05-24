package com.mylog.common.licence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.common.licence.entity.Result;
import com.mylog.common.licence.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.common.licence.model.DTO.UserDTO;
import com.mylog.common.licence.model.VO.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
public interface IUserService extends IService<User> {

    Result selectUserList(Page<User> page);

    Result selectOne(UserDTO userDTO);

    Result addUser(UserDTO userDTO);

    Result deleteOne(UserDTO userDTO);

    Result exchange(UserDTO userDTO);

    Result login(UserDTO userDTO);
}
