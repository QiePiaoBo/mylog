package com.mylog.common.licence.model.dto;

/**
 * @Classname UserInfoDTO
 * @Description UserInfoDTO
 * @Date 5/7/2022 4:59 PM
 */
public class UserInfoDTO {

    private Integer id;

    private Integer userId;

    private String realName;

    private Integer gender;

    private Integer idType;

    private String idCode;

    private String wechatCode;

    private String mail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", idType=" + idType +
                ", idCode='" + idCode + '\'' +
                ", wechatCode='" + wechatCode + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
