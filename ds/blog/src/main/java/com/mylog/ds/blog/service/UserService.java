package com.mylog.ds.blog.service;

import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.session.UserContext;
import org.springframework.stereotype.Service;

/**
 * 用户类服务
 * @author Dylan
 */
@Service
public class UserService extends UserContext{

    /**
     * 获取当前登录用户
     * @return
     */
    public Person getUser(){
        return super.getCurrentUser();
    }
}
