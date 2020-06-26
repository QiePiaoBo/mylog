package com.mylog.common.batch.datainfo;

/**
 * 数据源信息 wcb_batch
 * @author Dylan
 */
public enum TestDataInfo {

    /**
     * 驱动名
     */
    DRIVER_CLASS_NAME("com.mysql.cj.jdbc.Driver"),
    /**
     * url
     */
    URL("jdbc:mysql://logicer.top:3306/wcb_batch?&serverTimezone=UTC&characterEncoding=utf8"),
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

    TestDataInfo(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
