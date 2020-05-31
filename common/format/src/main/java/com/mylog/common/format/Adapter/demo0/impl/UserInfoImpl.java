package com.mylog.common.format.Adapter.demo0.impl;

import com.mylog.common.format.Adapter.demo0.IUserInfo;

/**
 * 我公司人员信息接口实现
 */
public class UserInfoImpl implements IUserInfo {

    @Override
    public String getUserName() {
        System.out.println("获取我公司人员的用户名");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("获取我公司人员的家庭住址");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("获取我公司人员的手机号码");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("获取我公司人员的公司电话");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("获取我公司人员的职位");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("获取我公司人员的家庭电话");
        return null;
    }
}
