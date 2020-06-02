package com.mylog.common.format.Proxy.Demo3;

/**
 * 强制代理
 */
public class Client {
    // 直接访问真实角色
//    public static void main(String[] args) {
//        // 定义游戏角色
//        IGamePlayer player = new GamePlayer("张三");
//        // 开始打游戏 打印时间
//        System.out.println("开打 20200602 08 00");
//        player.login("zhansan", "123456");
//        // 打怪
//        player.killBoss();
//        // 升级
//        player.upGrade();
//        // 结束
//        System.out.println("打完 20200602 09 00");
//    }
    // 直接访问代理类
//    public static void main(String[] args) {
//        // 定义一个游戏角色
//        IGamePlayer player = new GamePlayer("张三");
//        // 定义一个代练者
//        IGamePlayer proxy = new GamePlayerProxy(player);
//        // 开始打游戏 打印时间
//        System.out.println("开打 20200602 08 00");
//        proxy.login("zhansan", "123456");
//        // 打怪
//        proxy.killBoss();
//        // 升级
//        proxy.upGrade();
//        // 结束
//        System.out.println("打完 20200602 09 00");
//    }
    // 使用强制代理
    public static void main(String[] args) {
        // 定义一个游戏角色
        IGamePlayer player = new GamePlayer("张三");
        // 获得指定代理
        IGamePlayer proxy = player.getProxy();
        // 开始打游戏 打印时间
        System.out.println("开打 20200602 08 00");
        proxy.login("zhansan", "123456");
        // 打怪
        proxy.killBoss();
        // 升级
        proxy.upGrade();
        // 结束
        System.out.println("打完 20200602 09 00");
    }
}
