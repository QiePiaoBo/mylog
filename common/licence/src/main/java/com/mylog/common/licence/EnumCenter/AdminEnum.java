package com.mylog.common.licence.EnumCenter;

public enum  AdminEnum {
    ADMIN ("admin");
    private String userName;

    AdminEnum(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}