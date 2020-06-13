package com.mylog.ds.blog.service;

import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.session.UserContext;
import org.springframework.stereotype.Service;

/**
 * @author Dylan
 */
@Service
public class UserService extends UserContext{

    public Person getUser(){
        return super.getCurrentUser();
    }

}
