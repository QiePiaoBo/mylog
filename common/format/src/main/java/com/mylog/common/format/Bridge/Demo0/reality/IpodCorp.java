package com.mylog.common.format.Bridge.Demo0.reality;

import com.mylog.common.format.Bridge.Demo0.Corp;

/**
 * A货公司
 */
public class IpodCorp extends Corp {

    // 造A货
    @Override
    protected void produce() {
        System.out.println("生产'ipod'。。。");
    }

    // 卖A货
    @Override
    protected void sell() {
        System.out.println("ipod畅销。。。");
    }

    // 赚大钱
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("大赚一笔！！！");
    }
}
