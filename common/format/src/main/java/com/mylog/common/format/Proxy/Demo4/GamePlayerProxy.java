package com.mylog.common.format.Proxy.Demo4;

/**
 * 强制代理代理类
 */
public class GamePlayerProxy implements IGamePlayer, IProxy {
    private IGamePlayer gamePlayer = null;

    //构造函数传递用户名
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    //代练杀怪
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    //代练登录
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    //代练升级
    public void upGrade() {
        this.gamePlayer.upGrade();
        this.count();
    }

    //代理的代理暂时还没有，就是自己
    public IGamePlayer getProxy() {
        return this;
    }

    // 计算费用
    public void count(){
        System.out.println("目前为止的代练费为150元");
    }
}