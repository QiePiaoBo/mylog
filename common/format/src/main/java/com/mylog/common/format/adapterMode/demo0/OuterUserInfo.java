package com.mylog.common.format.adapterMode.demo0;

import java.util.Map;

public class OuterUserInfo extends OuterUserImpl implements IUserInfo {

    private Map baseInfo = super.getuserBaseInfo();

    private Map homeInfo = super.getUserHomeInfo();

    private Map officeInfo = super.getuserOfficeInfo();

    @Override
    public String getUserName() {
        String userName = (String) this.homeInfo.get("homeAddress");
        return userName;
    }

    @Override
    public String getHomeAddress() {
        return null;
    }

    @Override
    public String getMobileNumber() {
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        return null;
    }

    @Override
    public String getJobPosition() {
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        return null;
    }
}
