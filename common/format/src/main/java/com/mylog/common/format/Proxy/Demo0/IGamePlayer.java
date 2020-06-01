package com.mylog.common.format.Proxy.Demo0;

/**
 * 玩家接口
 */
public interface IGamePlayer {
    // 登录游戏
    public void login(String user, String password);
    // 杀怪，网络游戏主要特色
    public void killBoss();
    // 升级
    public void upGrade();
}
