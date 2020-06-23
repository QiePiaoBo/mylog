package com.mylog.common.batch.processors;

import com.mylog.common.batch.model.entity.MailEntity;
import com.mylog.common.batch.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 批量邮件发送processor
 * @author Dylan
 */
@Slf4j
@Component
public class MailSendProcessor implements ItemProcessor<MailEntity, MailEntity> {

    @Autowired
    IMailService mailService;


    @Override
    public MailEntity process(MailEntity mailEntity) throws Exception {

        String mail = mailEntity.getMail();
        log.info("mail is: " + mail);
        mailService.sendSimpleMail(mail, "项目部署", "今天项目运行正常");
        return mailEntity;
    }
}
