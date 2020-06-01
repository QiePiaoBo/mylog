package com.mylog.common.format.Bridge.Demo2;

/**
 * 抽象化角色
 */
public class Abstraction {
    // 定义对实现化角色的引用
    private Implementor implementor;
    // 约束子类必须实现该构造函数
    public Abstraction(Implementor impl) {
        this.implementor = impl;
    }
    // 自身的方法、属性
    public void request(){
        this.implementor.doSomething();
    }
    public Implementor getImpl(){
        return implementor;
    }
}
