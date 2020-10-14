package com.mylog.tools.entitys.entity;

/**
 * 返回值message
 * @author Dylan
 */
public enum  Message {
    /**
     * 成功
     */
    SUCCESS ("成功"),
    /**
     * 文件过大
     */
    OUTOF_SIZE_ERROR("文件过大"),
    /**
     * 入库失败
     */
    INSERT_ERROR ("插入数据库失败"),
    /**
     * 查询失败
     */
    QUERY_ERROR("查询失败"),
    /**
     * 更新失败
     */
    UPDATE_ERROR("更新失败"),
    /**
     * 权限不足
     */
    PERMISSION_ERROR("您没有权限执行此操作"),
    /**
     * 删除失败
     */
    DELETE_ERROR("删除失败"),
    /**
     * 上传失败
     */
    UPLOAD_ERROR("上传失败"),
    /**
     * 文件为空
     */
    FILE_NEED("文件为空"),
    /**
     * 参数缺失
     */
    PARAM_NEED("参数缺失")
    ;

    private String msg;

    Message(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }

}
