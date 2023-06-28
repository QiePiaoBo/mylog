package com.mylog.business.chat.model;

import lombok.Data;

/**
 * @Classname MsgQueryModel
 * @Description MsgQueryModel
 * @Date 6/20/2023 5:25 PM
 */
@Data
public class SessionQueryModel {

    private Integer sessionId;

    private Integer senderId;

    private Integer recipientId;

    private Integer talkTeamId;
}
