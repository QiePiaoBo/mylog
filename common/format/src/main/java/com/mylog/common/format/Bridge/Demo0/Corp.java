package com.mylog.common.format.Bridge.Demo0;

/**
 * 抽象公司
 */
public abstract class Corp {
    /**
     * 公司就该能生产
     */
    protected abstract void produce();

    /**
     * 公司就该能卖产品
     */
    protected abstract void sell();

    /**
     * 公司最重要的功能就是搞钱
     */
    public void makeMoney(){
        // 先生产
        this.produce();
        // 再卖钱
        this.sell();
    }
}
