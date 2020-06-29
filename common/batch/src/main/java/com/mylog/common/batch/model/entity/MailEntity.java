package com.mylog.common.batch.model.entity;

import lombok.Data;

/**
 * 邮件发送实体类
 * @author Dylan
 */
@Data
public class MailEntity {
    private String username;
    private int usergroup;
    private String phone;
    private String mail;

    public MailEntity(String username, int usergroup, String phone, String mail) {
        this.username = username;
        this.usergroup = usergroup;
        this.phone = phone;
        this.mail = mail;
    }
    public MailEntity(){

    }
}
