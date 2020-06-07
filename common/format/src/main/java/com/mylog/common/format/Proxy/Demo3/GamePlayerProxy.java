package com.mylog.common.format.Proxy.Demo3;

public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer gamePlayer = null;

    //构造函数传递用户名
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    //代练杀怪
    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    //代练登录
    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    //代练升级
    @Override
    public void upGrade() {
        this.gamePlayer.upGrade();
    }

    //代理的代理暂时还没有，就是自己
    @Override
    public IGamePlayer getProxy() {
        return this;
    }
}