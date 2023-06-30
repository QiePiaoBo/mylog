package com.mylog.business.chat.model;

import lombok.Data;

import java.util.Objects;

/**
 * @Classname MsgRecordEntity
 * @Description MsgRecordEntity
 * @Date 6/20/2023 5:14 PM
 */
@Data
public class SessionInsertModel {

    private Integer sessionId;

    private Integer senderId;

    private Integer recipientId;

    private Integer talkTeamId;

    public boolean isOk() {
        if (Objects.nonNull(getSenderId()) && Objects.nonNull(getRecipientId())){
            return true;
        }
        if (Objects.isNull(getSenderId()) && Objects.isNull(getRecipientId()) && Objects.nonNull(getTalkTeamId())){
            return true;
        }
        return false;
    }

}
