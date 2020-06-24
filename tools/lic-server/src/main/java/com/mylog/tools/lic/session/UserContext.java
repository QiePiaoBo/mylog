package com.mylog.tools.lic.session;

import com.mylog.tools.lic.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 保存和获取当前用户
 * @author Dylan
 */
@Service
public class UserContext {

    private static final String CURRENT_USER_IN_SESSION = "user";

    /**
     * 获取session
     * @return
     */
    public HttpSession getSession(){
        // SpringMVC获取session的方式通过RequestContextHolder
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession();
    }

    /**
     * 设置当前用户到session中
     * @param currentUser
     */
    public void putCurrentUser(Person currentUser){
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
    public Person getCurrentUser(){
        Object o = getSession().getAttribute(CURRENT_USER_IN_SESSION);
        Person p = (Person)o;
        return p;
    }

}
