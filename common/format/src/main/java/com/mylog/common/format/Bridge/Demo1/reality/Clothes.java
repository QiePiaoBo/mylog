package com.mylog.common.format.Bridge.Demo1.reality;

import com.mylog.common.format.Bridge.Demo1.Product;

/**
 * 服装产品类
 */
public class Clothes extends Product {
    // 做衣服
    public void beProducted() {
        System.out.println("生产出的衣服是这样的。。。");
    }
    // 卖衣服
    public void beSelled() {
        System.out.println("生产出的衣服都卖出去了。。。");
    }
}
