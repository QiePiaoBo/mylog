package com.mylog.common.format.Flyweight.Demo0;

public class Client {
    public static void main(String[] args) {
        //从工厂中获得一个对象
        SignInfo signInfo = SignInfoFactory.getSignInfo();
        //进行其他业务处理
    }
}