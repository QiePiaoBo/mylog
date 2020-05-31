package com.mylog.common.format.Adapter.demo0.impl;

import com.mylog.common.format.Adapter.demo0.IUserInfo;

import java.util.Map;

public class OuterUserInfo extends OuterUserImpl implements IUserInfo {

    private final Map<String, String> baseInfo = super.getuserBaseInfo();

    private final Map<String, String> homeInfo = super.getUserHomeInfo();

    private final Map<String, String> officeInfo = super.getuserOfficeInfo();

    @Override
    public String getUserName() {
        System.out.println("获取外部员工的姓名");
        return this.baseInfo.get("userName");
    }

    @Override
    public String getMobileNumber() {
        System.out.println("获取外部员工的手机号");
        return this.baseInfo.get("MobileNumber");
    }

    @Override
    public String getHomeAddress() {
        System.out.println("获取外部员工的家庭住址");
        return this.homeInfo.get("homeAddress");
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("获取外部员工的家庭电话");
        return this.homeInfo.get("homeTelNumber");
    }

    @Override
    public String getJobPosition() {
        System.out.println("获取外部员工的职位");
        return this.officeInfo.get("jobPosition");
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("获取外部员工的公司号码");
        return this.officeInfo.get("officeTelNumber");
    }
}
