package com.mylog.business.chat.dal.model;

/**
 * @Classname MsgQueryModel
 * @Description MsgQueryModel
 * @Date 6/20/2023 5:25 PM
 */
public class MsgQueryModel {

    private Integer fromId;

    private Integer toId;

    private String partition;

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }
}
