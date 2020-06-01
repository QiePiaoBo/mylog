package com.mylog.common.format.Proxy.Demo0;

/**
 * 代练玩家
 */
public class GamePlayerProxy implements IGamePlayer {

    private IGamePlayer gamePlayer = null;

    // 通过构造方法确定帮谁代练
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    // 代练上线
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    // 代练杀小怪，张飞吃豆芽
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    // 代练升级
    public void upGrade() {
        this.gamePlayer.upGrade();
    }
}
