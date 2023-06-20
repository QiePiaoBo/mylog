package com.mylog.business.chat.dal.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * @Classname MsgRecordEntity
 * @Description MsgRecordEntity
 * @Date 6/20/2023 5:14 PM
 */
public class MsgRecordEntity {

    @TableId
    private Long msgId;

    private Integer fromId;

    private Integer toId;

    private String msgContent;

    private String msgHash;

    private LocalDateTime msgTimestamp;

    private Integer delFlag;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgHash() {
        return msgHash;
    }

    public void setMsgHash(String msgHash) {
        this.msgHash = msgHash;
    }

    public LocalDateTime getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(LocalDateTime msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"msgId\":")
                .append(msgId);
        sb.append(",\"fromId\":")
                .append(fromId);
        sb.append(",\"toId\":")
                .append(toId);
        sb.append(",\"msgContent\":").append(msgContent == null ? "" : "\"")
                .append(msgContent).append(msgContent == null ? "" : "\"");
        sb.append(",\"msgHash\":").append(msgHash == null ? "" : "\"")
                .append(msgHash).append(msgHash == null ? "" : "\"");
        sb.append(",\"msgTimestamp\":")
                .append(msgTimestamp);
        sb.append(",\"delFlag\":")
                .append(delFlag);
        sb.append('}');
        return sb.toString();
    }
}
