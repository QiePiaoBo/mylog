package com.mylog.common.format.Bridge.Demo0.reality;

import com.mylog.common.format.Bridge.Demo0.Corp;

/**
 * 服装公司
 */
public class ClothesCorp extends Corp {

    // 做衣服
    @Override
    protected void produce() {
        System.out.println("做很好的衣服。。。");
    }

    // 卖衣服
    @Override
    protected void sell() {
        System.out.println("卖一般的价钱。。。");
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("服装公司赚了点小钱。。。");
    }
}
