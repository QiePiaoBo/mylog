package com.mylog.common.licence.EnumCenter;

public enum  ErrorEnum {

    INPUT_NEEDED     ("0001","参数缺失");

    private String ecode;

    private String emsg;

    ErrorEnum(String ecode, String emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public String getEcode() {
        return ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public static ErrorEnum statOf(String ecode) {
        for (ErrorEnum state : values())
            if (state.getEcode().equals(ecode))
                return state;
        return null;
    }
}