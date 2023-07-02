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
public class LgcTalkBlacklistEntity {


    private Integer id;

    private Integer blockUserId;

    private Integer blockedUserId;

    private String blockReason;

    private LocalDateTime createdAt;

    private LocalDateTime expirationAt;

    private Integer delFlag;

}
