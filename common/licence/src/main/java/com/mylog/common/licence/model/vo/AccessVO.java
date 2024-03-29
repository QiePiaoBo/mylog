package com.mylog.common.licence.model.vo;

/**
 * @author Dylan
 * @Date : 2022/5/9 - 23:30
 */
public class AccessVO {

    private Integer id;

    private String accessCode;

    private String accessName;

    private Integer accessType;

    private String accessUri;

    private String accessDescription;

    private Integer accessStatus;

    private Integer parentMenu;

    private Integer requestType;

    public Integer getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Integer parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public String getAccessUri() {
        return accessUri;
    }

    public void setAccessUri(String accessUri) {
        this.accessUri = accessUri;
    }

    public String getAccessDescription() {
        return accessDescription;
    }

    public void setAccessDescription(String accessDescription) {
        this.accessDescription = accessDescription;
    }

    public Integer getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(Integer accessStatus) {
        this.accessStatus = accessStatus;
    }

    @Override
    public String toString() {
        return "AccessVO{" +
                "id=" + id +
                ", accessCode='" + accessCode + '\'' +
                ", accessName='" + accessName + '\'' +
                ", accessType=" + accessType +
                ", accessUri='" + accessUri + '\'' +
                ", accessDescription='" + accessDescription + '\'' +
                ", accessStatus=" + accessStatus +
                '}';
    }
}
