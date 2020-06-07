package com.mylog.common.format.Bridge.Demo1.reality;

import com.mylog.common.format.Bridge.Demo1.Corp;
import com.mylog.common.format.Bridge.Demo1.Product;

/**
 * 山寨公司
 */
public class ShanZhaiCorp extends Corp {
    // 产什么产品不知道，被调用的时候才知道
    public ShanZhaiCorp(Product product) {
        super(product);
    }
    // 为了赚钱
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("这次投机投对了，赚大钱了啊！！！");
    }
}
