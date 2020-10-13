package com.mylog.common.batch.model.dto;


/**
 * @author Dylan
 * @Date : Created in 11:13 2020/6/29
 * @Description : java类
 */
public class MailContentDto {

    private String userType;
    private String mailSubject;
    private String mailContent;
    private String otherInfo;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
