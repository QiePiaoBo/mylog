package com.mylog.common.licence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.mylog.tools.lic.entity.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class User extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户权限组
     */
    private String usergroup;

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
     * 用户身份证号
     */
    private String cnId;


    /**
     * 用户身份证号
     */
    private boolean isDel;

    /**
     * 备用字段1
     */
    private String description;

}
