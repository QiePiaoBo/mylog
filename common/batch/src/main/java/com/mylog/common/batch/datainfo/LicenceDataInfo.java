package com.mylog.common.batch.datainfo;

public enum LicenceDataInfo {

    DRIVER_CLASS_NAME("com.mysql.cj.jdbc.Driver"),
    URL("jdbc:mysql://logicer.top:3306/ds?&serverTimezone=UTC&characterEncoding=utf8"),
    USER_NAME("root"),
    PASSWORD("1997o4")

    ;
    private String value;

    LicenceDataInfo(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
