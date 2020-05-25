package com.mylog.common.licence.session;

import com.mylog.common.licence.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 保存和获取当前用户
 */
@Service
public class UserContext {

    private static final String CURRENT_USER_IN_SESSION = "user";

    /**
     * 获取session
     * @return
     */
    private HttpSession getSession(){
        // SpringMVC获取session的方式通过RequestContextHolder
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession();
    }

    /**
     * 设置当前用户到session中
     * @param currentUser
     */
    public void putCurrentUser(User currentUser){
        getSession().setAttribute(CURRENT_USER_IN_SESSION, currentUser);
    }

    /**
     * 删除当前的人的session
     */
    public void deleteCurrentUser(){
        getSession().removeAttribute(CURRENT_USER_IN_SESSION);
    }
    /**
     * 获取当前用户
     * @return
     */
    public User getCurrentUser(){
        return (User) getSession().getAttribute(CURRENT_USER_IN_SESSION);
    }

}
