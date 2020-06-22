package com.mylog.common.batch.datainfo;

public enum BlogDataInfo {

    DRIVER_CLASS_NAME("com.mysql.cj.jdbc.Driver"),
    URL("jdbc:mysql://logicer.top:3306/ds_blog?&serverTimezone=UTC&characterEncoding=utf8"),
    USER_NAME("root"),
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
