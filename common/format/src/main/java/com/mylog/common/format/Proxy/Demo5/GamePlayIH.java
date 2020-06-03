package com.mylog.common.format.Proxy.Demo5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK提供的动态代理接口实现
 */
public class GamePlayIH implements InvocationHandler {
    // 被代理者
    Class cls = null;
    // 被代理的实例
    Object obj = null;
    // 我要代理谁
    public GamePlayIH(Object _obj){
        this.obj = _obj;
    }
    // 调用代理的方法
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable{
        Object result = method.invoke(this.obj, args);
        // 如果是登录方法就发送信息
        if (method.getName().equalsIgnoreCase("login")){
            System.out.println("您的账号正在登陆");
        }
        return result;
    }

}
