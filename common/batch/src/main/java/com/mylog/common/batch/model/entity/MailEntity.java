package com.mylog.common.batch.model.entity;

/**
 * 邮件发送实体类
 * @author Dylan
 */
public class MailEntity {
    private int id;
    private String username;
    private int usergroup;
    private String phone;
    private String mail;

    public MailEntity(int id, int usergroup, String username, String phone, String mail) {
        this.id = id;
        this.username = username;
        this.usergroup = usergroup;
        this.phone = phone;
        this.mail = mail;
    }
    public MailEntity(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
