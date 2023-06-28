package com.mylog.business.chat.dal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname MsgRecordEntity
 * @Description MsgRecordEntity
 * @Date 6/20/2023 5:14 PM
 */
@Data
public class MsgRecordEntity {

    @TableId
    private Long msgId;

    /**
     * 会话Id
     */
    private String sessionId;

    /**
     * 消息类型 logicer talk command
     */
    private Integer msgType;

    /**
     * 发送者
     */
    private Integer fromId;

    /**
     * 接收者
     */
    private Integer toId;

    /**
     * 消息体
     */
    private String msgContent;

    /**
     * 消息体hash值
     */
    private String msgHash;

    private LocalDateTime msgTimestamp;

    private Integer delFlag;

}