package com.mylog.common.format.Bridge.Demo1.reality;

import com.mylog.common.format.Bridge.Demo1.Product;

/**
 * 房子产品类
 */
public class House extends Product {

    // 豆腐渣就豆腐渣 好歹也是房子
    public void beProducted() {
        System.out.println("生产出的房子是这样的。。。");
    }

    // 虽然是豆腐渣 但也能卖出去
    public void beSelled() {
        System.out.println("生产的房子卖出去了。。。");
    }
}
