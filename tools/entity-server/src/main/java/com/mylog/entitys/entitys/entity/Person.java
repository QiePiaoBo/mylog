package com.mylog.entitys.entitys.entity;


import java.io.Serializable;

/**
 * @author Dylan
 * @since 2020-05-24
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 密码
     */
    private String password;

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

    /**
     * 用户真实姓名
     */
    private String realname;

    /**
     * 用户是否已被删除
     */
    private boolean isDel;

    /**
     * 用户身份证号
     */
    private String cnId;

    /**
     * 备用字段1
     */
    private String description;

    public Person(Integer id, Integer usergroup,
                  String username, String password,
                  String phone, String mail,
                  String gender, String realname,
                  String cnId, Boolean isDel, String description) {
        this.id = id;
        this.usergroup = usergroup;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
        this.gender = gender;
        this.realname = realname;
        this.cnId = cnId;
        this.isDel = isDel;
        this.description = description;
    }

    public Person() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    public String getCnId() {
        return cnId;
    }

    public void setCnId(String cnId) {
        this.cnId = cnId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
