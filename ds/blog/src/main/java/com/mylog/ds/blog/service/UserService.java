package com.mylog.ds.blog.service;

import com.mylog.entitys.entitys.vo.PersonVo;
import com.mylog.entitys.utils.session.UserContext;
import org.springframework.stereotype.Service;

/**
 * 用户类服务
 * @author Dylan
 */
@Service
public class UserService{
    /**
     * 获取当前登录用户
     * @return
     */
    public PersonVo getUser(){
        PersonVo p = UserContext.getCurrentUser();
        return p;
    }
}
