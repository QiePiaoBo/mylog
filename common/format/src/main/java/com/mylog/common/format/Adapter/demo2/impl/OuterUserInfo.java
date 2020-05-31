package com.mylog.common.format.Adapter.demo2.impl;


import com.mylog.common.format.Adapter.demo2.IOuterUserBaseInfo;
import com.mylog.common.format.Adapter.demo2.IOuterUserHomeInfo;
import com.mylog.common.format.Adapter.demo2.IOuterUserOfficeInfo;
import com.mylog.common.format.Adapter.demo2.IUserInfo;

import java.util.Map;

public class OuterUserInfo implements IUserInfo {
    // 源目标对象
    private IOuterUserBaseInfo baseInfo = null;
    private IOuterUserHomeInfo homeInfo = null;
    private IOuterUserOfficeInfo officeInfo = null;

    // 数据处理
    private Map<String, String> baseMap = null;
    private Map<String, String> homeMap = null;
    private Map<String, String> officeMap = null;

    // 构造函数传递对象
    public OuterUserInfo(IOuterUserBaseInfo _baseInfo,
                         IOuterUserHomeInfo _homeInfo,
                         IOuterUserOfficeInfo _officeInfo) {
        this.baseInfo = _baseInfo;
        this.homeInfo = _homeInfo;
        this.officeInfo = _officeInfo;

        // 数据处理
        this.baseMap = this.baseInfo.getUserBaseInfo();
        this.homeMap = this.homeInfo.getUserHomeInfo();
        this.officeMap = this.officeInfo.getUserOfficeInfo();
    }

    // 获取姓名
    public String getUserName() {
        return this.baseMap.get("userName");
    }

    // 获取家庭住址
    public String getHomeAddress() {
        return this.homeMap.get("homeAddress");
    }

    // 获取手机号
    public String getMobileNumber() {
        System.out.println(baseMap.get("MobileNumber"));
        return baseMap.get("MobileNumber");
    }

    // 获取办公室电话
    public String getOfficeTelNumber() {
        return officeMap.get("officeTelNumber");
    }

    // 获取公司职位
    public String getJobPosition() {
        return officeMap.get("jobPosition");
    }

    // 获取家庭号码
    public String getHomeTelNumber() {
        return homeMap.get("homeTelNumber");
    }
}
