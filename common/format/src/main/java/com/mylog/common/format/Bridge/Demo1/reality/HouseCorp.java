package com.mylog.common.format.Bridge.Demo1.reality;

import com.mylog.common.format.Bridge.Demo1.Corp;

/**
 * 房地产公司
 */
public class HouseCorp extends Corp {

    // 传递一个House产品进来
    public HouseCorp(House house) {
        super(house);
    }
    // 开始赚钱
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("赚大钱了啊");
    }
}
