package com.mylog.common.licence.enumcenter;

/**
 * @author Dylan
 */
public enum  AdminEnum {
    /**
     * admin
     */
    ADMIN ("admin");
    private String userName;

    AdminEnum(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}
