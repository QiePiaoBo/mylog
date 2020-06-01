package com.mylog.common.format.Bridge.Demo2;

/**
 * 实现化角色实现2
 */
public class ConcreteImplementor2 implements Implementor {
    @Override
    public void doSomething() {
        // 业务逻辑处理
        System.out.println("002.你可以做点你想做的事。");
    }

    @Override
    public void doAnything() {
        // 业务处理逻辑
        System.out.println("002.你什么都不能做。");
    }
}
