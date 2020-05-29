package com.mylog.common.format.adapterMode.demo0;

import java.util.HashMap;
import java.util.Map;

public class OuterUserImpl implements IOutUser {

    @Override
    public Map getuserBaseInfo() {
        HashMap baseInfo = new HashMap();
        baseInfo.put("userName", "张三");
        baseInfo.put("MobileNumber", "13812345678");
        return baseInfo;
    }

    @Override
    public Map getuserOfficeInfo() {
        HashMap officeInfo = new HashMap();
        officeInfo.put("jobPosition","BOSS");
        officeInfo.put("officeTelNumber","93699339");
        return officeInfo;
    }

    @Override
    public Map getUserHomeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeAddress", "上海市静安区");
        homeInfo.put("homeTelNumber", "7740137");
        return homeInfo;
    }
}
