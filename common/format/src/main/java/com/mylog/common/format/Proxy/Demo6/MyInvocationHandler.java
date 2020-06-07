package com.mylog.common.format.Proxy.Demo6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    // 被代理者
    Class cls = null;
    // 被代理的实例
    Object obj = null;
    // 我要代理谁
    public MyInvocationHandler(Object _obj){
        this.obj = _obj;
    }
    // 调用代理的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        Object result = method.invoke(this.obj, args);
        // 如果是登录方法就发送信息
        if ("login".equalsIgnoreCase(method.getName())){
            System.out.println("您的账号正在登陆");
        }
        return result;
    }
}