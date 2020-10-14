package com.mylog.tools.entitys.entity;

/**
 * 状态码枚举类
 * @author Dylan
 */
public enum  Status {
    /**
     * 成功
     */
    SUCCESS (100000),
    /**
     * 文件过大
     */
    OUTOF_SIZE_ERROR(100001),
    /**
     * 入库失败
     */
    INSERT_ERROR (100002),
    /**
     * 查询失败
     */
    QUERY_ERROR(100003),
    /**
     * 更新失败
     */
    UPDATE_ERROR (100004),
    /**
     * 权限不足
     */
    PERMISSION_ERROR (100005),
    /**
     * 删除失败
     */
    DELETE_ERROR(100006),
    /**
     * 上传失败
     */
    UPLOAD_ERROR(100007),
    /**
     * 文件为空
     */
    FILE_NEED(100008)
    ;

    private long status;

    Status(Integer statusNum){
        this.status = statusNum;
    }

    public long getStatus(){
        return status;
    }
}