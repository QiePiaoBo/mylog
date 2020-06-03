package com.mylog.common.format.Proxy.Demo6;

import java.lang.reflect.InvocationHandler;

/**
 * 动态代理02
 */
public class Client {
    // 扩展前
//    public static void main(String[] args) {
//        // 定义一个主题
//        Subject subject = new RealSubject();
//        // 定义一个handler
//        InvocationHandler handler = new MyInvocationHandler(subject);
//        // 定义主题的代理
//        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),
//                subject.getClass().getInterfaces(), handler);
//        // 代理的行为
//        proxy.doSomething("Finish");
//    }
    // 扩展后
    public static void main(String[] args) {
        // 定义一个主题
        Subject subject = new RealSubject();
        // 定义主题的代理
        Subject proxy = SubjectDynamicProxy.newProxyInstance(subject);
        // 代理的行为
        proxy.doSomething("Finish");
    }
}
