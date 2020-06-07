package com.mylog.common.format.Bridge.Demo1.reality;

import com.mylog.common.format.Bridge.Demo1.Product;

/**
 * Ipod 产品类
 */
public class Ipod extends Product {

    @Override
    public void beProducted() {
        System.out.println("生产出的ipod是这样的。。。");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的ipod卖出去了。。。");
    }
}
