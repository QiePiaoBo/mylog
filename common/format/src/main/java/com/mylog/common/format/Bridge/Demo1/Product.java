package com.mylog.common.format.Bridge.Demo1;

/**
 * 抽象产品类
 */
public abstract class Product {
    // 不管是什么产品，总会被生产出来
    public abstract void beProducted();

    // 生产出来的东西，一定要销售出去，否则会亏本
    public abstract void beSelled();
}
