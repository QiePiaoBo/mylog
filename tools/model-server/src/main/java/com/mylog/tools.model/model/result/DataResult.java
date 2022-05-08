package com.mylog.tools.model.model.result;

import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;

/**
 * @author Dylan
 * @description 数据相关的result
 */
public class DataResult extends HttpResult{

    private final long status;

    private final String message;

    private final long page;

    private final long size;

    private final long total;

    private final Object data;


    /**
     * 成功
     * @return
     */
    public static DataResult.Builder success(){
        //相当于调用下面的map 然后把值存map里面。
        return DataResult.getBuilder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg());
    }

    /**
     * 失败
     * @return
     */
    public static DataResult.Builder fail(){
        return DataResult.getBuilder(Status.ERROR_BASE.getStatus(), Message.ERROR.getMsg());
    }

    /**
     * 失败 自定义原因
     * @return
     */
    public static DataResult.Builder fail(Long status, String msg){
        return DataResult.getBuilder(status, msg);
    }

    /**
     * 获取Builder
     * @return
     */
    public static DataResult.Builder getBuilder(Long status, String msg){
        return new Builder(status, msg);
    }

    /**
     * 获取Builder
     * @return
     */
    public static DataResult.Builder getBuilder(){
        return DataResult.getBuilder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg());
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

        public DataResult build(){
            return new DataResult(this);
        }
    }

    private DataResult(Builder builder){
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
