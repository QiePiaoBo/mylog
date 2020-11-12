package com.mylog.entitys.entitys.entity;

/**
 * @author Dylan
 */
public class Result {

    private final long status;

    private final String message;

    private final long page;

    private final long size;

    private final long total;

    private final Object data;


    public static Result success(){
        //相当于调用下面的map 然后把值存map里面。
        return new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).build();
    }

    public static class Builder {
        private final long status;

        private final String message;

        private Object data;

        private long page;

        private long size;

        private long total;

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

        public Builder page(long page){
            this.page = page;
            return this;
        }

        public Builder size(long size){
            this.size = size;
            return this;
        }

        public Builder total(long total){
            this.total = total;
            return this;
        }

        public Result build(){
            return new Result(this);
        }
    }

    private Result(Builder builder){
        status = builder.status;
        message = builder.message;
        data = builder.data;
        page = builder.page;
        size = builder.size;
        total = builder.total;
    }

    public long getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getPage() {
        return page;
    }

    public long getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public Object getData() {
        return data;
    }
}
