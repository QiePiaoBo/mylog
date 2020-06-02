package com.mylog.common.format.Proxy.Demo6;

public class BeforeAdvice implements IAdvice {
    public void exec() {
        System.out.println("我是前置通知，特来通知");
    }
}
