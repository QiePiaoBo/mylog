package com.mylog.common.batch.model.dto;

import lombok.Data;

/**
 * @author Dylan
 * @Date : Created in 11:13 2020/6/29
 * @Description : java类
 */
@Data
public class MailContentDto {

    private String userType;
    private String mailSubject;
    private String mailContent;
    private String otherInfo;

}
