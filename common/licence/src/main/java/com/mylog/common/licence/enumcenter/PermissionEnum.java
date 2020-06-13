package com.mylog.common.licence.enumcenter;

/**
 * @author Dylan
 */
public enum  PermissionEnum {
    /**
     * 超级管理权限
     */
    ADMIN (0),
    /**
     * 管理员权限
     */
    MANAGER(1),
    /**
     * 用户权限
     */
    USER(2);
    private Integer groupId;

    PermissionEnum(Integer groupId){
        this.groupId = groupId;
    }

    public Integer getGroupId(){
        return groupId;
    }
}
