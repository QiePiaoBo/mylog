package com.mylog.business.chat.model;

import java.time.LocalDateTime;

/**
 * @Classname MsgRecordEntity
 * @Description MsgRecordEntity
 * @Date 6/20/2023 5:14 PM
 */
public class MsgInsertModel {

    /**
     * 会话Id
     */
    private String sessionId;

    /**
     * 消息类型 logicer talk command
     */
    private Integer msgType;

    private Integer fromId;

    private Integer toId;

    private String msgContent;

    private String msgHash;

    private LocalDateTime msgTimestamp;

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
        this.msgHash = "MD5.md5(msgContent)";
    }

    public String getMsgHash() {
        return msgHash;
    }

    public LocalDateTime getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(LocalDateTime msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"fromId\":")
                .append(fromId);
        sb.append(",\"toId\":")
                .append(toId);
        sb.append(",\"msgContent\":").append(msgContent == null ? "" : "\"")
                .append(msgContent).append(msgContent == null ? "" : "\"");
        sb.append(",\"msgHash\":").append(msgHash == null ? "" : "\"")
                .append(msgHash).append(msgHash == null ? "" : "\"");
        sb.append(",\"msgTimestamp\":")
                .append(msgTimestamp);
        sb.append('}');
        return sb.toString();
    }
}
