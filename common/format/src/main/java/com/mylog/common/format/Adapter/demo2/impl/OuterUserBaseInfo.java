package com.mylog.common.format.Adapter.demo2.impl;

import com.mylog.common.format.Adapter.demo2.IOuterUserBaseInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本信息接口实现
 */
public class OuterUserBaseInfo implements IOuterUserBaseInfo {
    @Override
    public Map<String, String> getUserBaseInfo() {
        HashMap<String, String> baseInfo = new HashMap<>();
        baseInfo.put("userName","这个员工交混世魔王");
        baseInfo.put("MobileNumber", "这个员工的电话是。。。");
        return baseInfo;
    }
}
