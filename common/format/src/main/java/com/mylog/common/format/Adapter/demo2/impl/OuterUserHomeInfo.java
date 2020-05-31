package com.mylog.common.format.Adapter.demo2.impl;

import com.mylog.common.format.Adapter.demo2.IOuterUserHomeInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 家庭信息实现
 */
public class OuterUserHomeInfo implements IOuterUserHomeInfo {

    @Override
    public Map<String, String> getUserHomeInfo() {

        HashMap<String, String> homeInfo = new HashMap<>();
        homeInfo.put("homeTelNumber", "员工的家庭电话是...");
        homeInfo.put("homeAddress", "员工的家住在...");
        return homeInfo;
    }
}
