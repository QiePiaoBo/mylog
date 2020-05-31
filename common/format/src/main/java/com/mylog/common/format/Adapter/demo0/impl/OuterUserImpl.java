package com.mylog.common.format.Adapter.demo0.impl;

import com.mylog.common.format.Adapter.demo0.IOutUser;

import java.util.HashMap;
import java.util.Map;

public class OuterUserImpl implements IOutUser {

    @Override
    public Map<String, String> getuserBaseInfo() {
        HashMap<String, String> baseInfo = new HashMap<String, String>();
        baseInfo.put("userName", "张三");
        baseInfo.put("MobileNumber", "13812345678");
        return baseInfo;
    }

    @Override
    public Map<String, String> getuserOfficeInfo() {
        HashMap<String, String> officeInfo = new HashMap<>();
        officeInfo.put("jobPosition","BOSS");
        officeInfo.put("officeTelNumber","93699339");
        return officeInfo;
    }

    @Override
    public Map<String, String> getUserHomeInfo() {
        HashMap<String, String> homeInfo = new HashMap<>();
        homeInfo.put("homeAddress", "上海市静安区");
        homeInfo.put("homeTelNumber", "7740137");
        return homeInfo;
    }
}
