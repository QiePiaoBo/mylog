package com.mylog.common.licence.EnumCenter;

public enum  PermissionEnum {
    ADMIN (0),
    MANAGER(1),
    USER(2);
    private Integer groupId;

    PermissionEnum(Integer groupId){
        this.groupId = groupId;
    }

    public Integer getGroupId(){
        return groupId;
    }
}
