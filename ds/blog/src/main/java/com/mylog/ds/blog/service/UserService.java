package com.mylog.ds.blog.service;

import com.mylog.ds.blog.entity.vo.UserVO;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.session.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户类服务
 * @author Dylan
 */
@Service
public class UserService{

    @Autowired
    UserContext userContext;
    /**
     * 获取当前登录用户
     * @return
     */
    public UserVO getUser(){
        Person p = userContext.getCurrentUser();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(p, userVO);
        return userVO;
    }
}
