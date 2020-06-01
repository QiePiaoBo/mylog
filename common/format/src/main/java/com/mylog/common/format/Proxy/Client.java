package com.mylog.common.format.Proxy;

import com.mylog.common.format.Proxy.Demo0.GamePlayer;
import com.mylog.common.format.Proxy.Demo1.Proxy;
import com.mylog.common.format.Proxy.Demo1.RealSubject;
import com.mylog.common.format.Proxy.Demo1.Subject;
import com.mylog.common.format.Proxy.Demo2.GamePlayerProxy;
import com.mylog.common.format.Proxy.Demo2.IGamePlayer;

public class Client {

    // 纯手打
//    public static void main(String[] args) {
//        // 定义一个网瘾少年
//        IGamePlayer boy = new GamePlayer("刘谋");
//        // 开始打游戏
//        System.out.println("game start time is 2020-06-01 12:00");
//        boy.login("PDD","123456");
//        // 开始杀怪
//        boy.killBoss();
//        // 升级
//        boy.upGrade();
//        // 不玩了
//        System.out.println("game start time is 2020-06-02 1:00");
//    }
    // 请代练
//    public static void main(String[] args) {
//        // 定义 谁请了代练
//        IGamePlayer player = new GamePlayer("刘谋");
//        // 定义 请来的代练
//        IGamePlayer proxy = new GamePlayerProxy(player);
//        // 开始打游戏
//        System.out.println("2020-06-02 12:00 辣个蓝人上线了");
//        proxy.login("PDD", "123456");
//        // 开始杀怪
//        proxy.killBoss();
//        proxy.upGrade();
//        proxy.killBoss();
//        proxy.upGrade();
//        System.out.println("2020-06-02 14:30 辣个蓝人下线了，变强啦");
//    }
    // 通用代理类图实现方法
//    public static void main(String[] args) {
//        // 谁需要代理
//        Subject subject = new RealSubject();
//        // 谁来代理
//        Subject proxy = new Proxy(subject);
//        proxy.request();
//    }
    // 普通代理
    public static void main(String[] args) {
        // 定义一个代练玩家
        IGamePlayer proxy = new GamePlayerProxy("刘谋");
        // time
        System.out.println("开始时间 2020-06-01 17:30");
        proxy.login("PDD","123456");
        // 开打
        proxy.killBoss();
        // 升级
        proxy.upGrade();
        // 下机
        System.out.println("结束时间 2020-06-02 03：00");
    }

}
