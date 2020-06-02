package com.mylog.common.format.Proxy.Demo5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理01
 */
public class Client {
    public static void main(String[] args) throws Throwable{
        // 定义一个痴迷的玩家
        IGamePlayer player = new GamePlayer("张三");
        // 定义一个Handler
        InvocationHandler handler = new GamePlayIH(player);
        System.out.println("开始时间 2020-06-01 17:30");
        // 获得类的class loader
        ClassLoader cl = player.getClass().getClassLoader();
        // 动态产生一个代理者
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);
        // 登录 杀怪 升级
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upGrade();
        // 记录游戏结束时间
        System.out.println("结束时间 2020-06-02 03：00");

    }
}
