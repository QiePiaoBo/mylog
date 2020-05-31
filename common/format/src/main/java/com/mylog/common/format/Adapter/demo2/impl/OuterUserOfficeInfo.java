package com.mylog.common.format.Adapter.demo2.impl;

import com.mylog.common.format.Adapter.demo2.IOuterUserOfficeInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 公司信息接口实现
 */
public class OuterUserOfficeInfo implements IOuterUserOfficeInfo {

    @Override
    public Map<String, String> getUserOfficeInfo() {
        HashMap<String, String> officeInfo = new HashMap<>();
        officeInfo.put("jobPosition", "这个人的职位是...");
        officeInfo.put("officeTelNumber", "这个人的办公电话是...");
        return officeInfo;
    }
}
