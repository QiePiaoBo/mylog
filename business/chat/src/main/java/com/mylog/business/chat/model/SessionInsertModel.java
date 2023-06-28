package com.mylog.business.chat.model;

import lombok.Data;

/**
 * @Classname MsgRecordEntity
 * @Description MsgRecordEntity
 * @Date 6/20/2023 5:14 PM
 */
@Data
public class SessionInsertModel {

    private Integer senderId;

    private Integer recipientId;

    private Integer talkTeamId;

}
