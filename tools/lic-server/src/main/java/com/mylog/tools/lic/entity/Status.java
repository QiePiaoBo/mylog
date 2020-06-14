package com.mylog.tools.lic.entity;

/**
 * 状态码枚举类
 * @author Dylan
 */
public enum  Status {
    /**
     * 成功
     */
    SUCCESS (100000);

    private long status;

    Status(Integer size){
        this.status = size;
    }

    public long getStatus(){
        return status;
    }
}
