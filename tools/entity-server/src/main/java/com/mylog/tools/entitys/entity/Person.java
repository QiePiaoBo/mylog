package com.mylog.tools.entitys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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


}
