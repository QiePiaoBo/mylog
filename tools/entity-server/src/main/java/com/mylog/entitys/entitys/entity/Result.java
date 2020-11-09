package com.mylog.entitys.entitys.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dylan
 */
public class Result {

    private Map<String,Object> result = new LinkedHashMap<>();

    private final long status;

    private final String message;

    private Object data;


    public static Result success(){
        //相当于调用下面的map 然后把值存map里面。
        return new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).build();
    }

    public static class Builder {
        private final long status;

        private final String message;

        private Object data;

        public Builder(long status, String message){
            this.status = status;
            this.message = message;
        }

        public Builder() {
            this.status = Status.SUCCESS.getStatus();
            this.message = Message.SUCCESS.getMsg();
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Result build(){
            return new Result(this);
        }
    }

    /**
     * 用来存值，对象，集合
     * @param key
     * @param value
     * @return
     */
    public  Result put (String key,Object value){
        this.result.put(key,value);
        return this;
    }
    public void setMap(Map<String, Object> map) {
        this.result = map;
    }

    private Result(Builder builder){
        status = builder.status;
        message = builder.message;
        data = builder.data;
    }
}
