package com.mylog.common.licence.EnumCenter;

public enum  GroupEnum {

    SUPER_USER      ("0","超管"),
    MANAGER_USER    ("1","管理员用户"),
    USER_GROUP  ("2","普通用户");

    private String groupId;
    private String groupName;

    GroupEnum(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}