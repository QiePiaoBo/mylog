package com.mylog.common.format.Bridge.Demo2;

/**
 * 实现化角色实现1
 */
public class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {
        // 业务逻辑处理
        System.out.println("001.You can do something you want.");
    }

    @Override
    public void doAnything() {
        // 业务处理逻辑
        System.out.println("001.You can not do anything.");
    }
}
