package com.mylog.common.format.Bridge.Demo0.reality;

import com.mylog.common.format.Bridge.Demo0.Corp;

/**
 * 房地产公司
 */
public class HouseCorp extends Corp {

    // 房地产公司造房子
    protected void produce() {
        System.out.println("盖呀盖呀盖大房。。。");
    }

    // 房地产公司卖房子
    protected void sell() {
        System.out.println("卖呀卖呀卖贵点。。。");
    }

    // 房地产公司赚钱
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚大钱了！！！");
    }
}
