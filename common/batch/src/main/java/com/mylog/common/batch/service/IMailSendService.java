package com.mylog.common.batch.service;

/**
 * @author Dylan
 * @Date : Created in 11:32 2020/6/23
 * @Description : 批量邮件发送job调用
 */
public interface IMailSendService {

    /**
     * 向管理员发送邮件
     * @return
     */
    String adminSendMail();

    /**
     * 向用户发送邮件
     * @return
     */
    String userSendMail();

}
