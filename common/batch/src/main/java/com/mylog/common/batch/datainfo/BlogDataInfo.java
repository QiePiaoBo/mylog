package com.mylog.common.batch.datainfo;

/**
 * 数据源信息 ds_blog
 * @author Dylan
 */
public enum BlogDataInfo {

    /**
     * 连接方法
     */
    DRIVER_CLASS_NAME("com.mysql.cj.jdbc.Driver"),
    /**
     * url
     */
    URL("jdbc:mysql://logicer.top:3306/ds_blog?&serverTimezone=UTC&characterEncoding=utf8"),
    /**
     * 用户名
     */
    USER_NAME("root"),
    /**
     * 密码
     */
    PASSWORD("1997o4")

    ;
    private String value;

    BlogDataInfo(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
