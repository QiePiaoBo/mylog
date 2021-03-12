package com.mylog.entitys.entitys.result;


import com.mylog.entitys.entitys.info.Message;
import com.mylog.entitys.entitys.info.Status;

/**
 * @author Dylan
 * @Date : Created in 10:51 2021/3/12
 * @Description : http相关的result
 * @Function :
 */
public class HttpResult {

    /**
     * 返回码
     */
    private long code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回具体数据
     */
    private Object data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpResult(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public HttpResult() {
    }

    public static HttpResult failed(){
        return new HttpResult(Status.ERROR_BASE.getStatus(), Message.ERROR.getMsg(), "");
    }
}
