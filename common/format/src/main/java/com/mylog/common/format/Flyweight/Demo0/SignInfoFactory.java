package com.mylog.common.format.Flyweight.Demo0;

public class SignInfoFactory {
    //报名信息的对象工厂
    public static SignInfo getSignInfo(){
        System.out.println("学员信息");
        return new SignInfo();
    }
}