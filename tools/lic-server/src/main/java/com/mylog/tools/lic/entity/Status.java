package com.mylog.tools.lic.entity;

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
    INSERT_ERROR (100002);

    private long status;

    Status(Integer status_num){
        this.status = status_num;
    }

    public long getStatus(){
        return status;
    }
}
