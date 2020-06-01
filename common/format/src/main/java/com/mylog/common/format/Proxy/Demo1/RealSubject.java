package com.mylog.common.format.Proxy.Demo1;

/**
 * 具体实现类、被代理类
 */
public class RealSubject implements Subject {
    // 实现方法
    public void request(){
        System.out.println("真实主题类在苦兮兮地实现request方法中。。。");
    }
}
