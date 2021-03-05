package com.mylog.entitys.entitys.vo;


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
    private Integer usergroup;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String mail;

    /**
     * 用户性别
     */
    private String gender;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(Integer usergroup) {
        this.usergroup = usergroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
