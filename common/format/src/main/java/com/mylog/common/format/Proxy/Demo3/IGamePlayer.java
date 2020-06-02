package com.mylog.common.format.Proxy.Demo3;

public interface IGamePlayer {

    // 登录游戏
    public void login(String user, String password);

    // 打怪
    public void killBoss();

    // 升级
    public void upGrade();

    // 设置代理
    public IGamePlayer getProxy();

}
