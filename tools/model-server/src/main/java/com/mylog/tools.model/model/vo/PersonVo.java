package com.mylog.tools.model.model.vo;


/**
 * @author Dylan
 * @Date : Created in 10:57 2020/10/14
 * @Description :
 * @Function :
 */
public class PersonVo {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户权限组
     */
    private Integer userGroup;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机
     */
    private String userPhone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Integer userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
