package com.mylog.common.format.Proxy.Demo0;

/**
 * 玩家
 */
public class GamePlayer implements IGamePlayer{

    private String name = "";

    public GamePlayer(String _name) {
        this.name = _name;
    }

    // 登录接口
    public void login(String user, String password) {
        System.out.println("登录名为" + user + "的玩家" + this.name + "登陆成功");
    }
    // 打怪
    public void killBoss() {
        System.out.println(this.name + "在杀怪");
    }

    // 升级，做任务升级或花钱升级
    public void upGrade() {
        System.out.println(this.name + "又升了一级。");
    }
}
