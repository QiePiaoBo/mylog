package com.mylog.common.format.Flyweight.Demo4;

public class Client {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        flyweightFactory.getFlyweight("aaa").setIntrinsic("hello");
        flyweightFactory.getFlyweight("aaa").setIntrinsic("hello");
        flyweightFactory.getFlyweight("aaa").setIntrinsic("hello");
        flyweightFactory.getFlyweight("bbb").setIntrinsic("hello");
        flyweightFactory.getFlyweight("bbb").setIntrinsic("hello");
        flyweightFactory.getFlyweight("bbb").setIntrinsic("hello");
    }
}
