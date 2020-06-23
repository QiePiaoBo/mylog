package com.mylog.common.batch.service;

/**
 * @author Dylan
 * @Date : Created in 11:32 2020/6/23
 * @Description : 批量邮件发送job调用
 */
public interface IMailSendService {

    String adminSendMail();
    String userSendMail();

}
