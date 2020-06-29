package com.mylog.common.batch.processors;

import com.mylog.common.batch.model.entity.MailEntity;
import com.mylog.common.batch.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 批量邮件发送processor
 * @author Dylan
 */
@Slf4j
public class CommonMailSendProcessor implements ItemProcessor<MailEntity, MailEntity> {

    @Autowired
    IMailService mailService;

    private String subject;
    private String content;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public MailEntity process(MailEntity mailEntity) throws Exception {

        String mail = mailEntity.getMail();
        log.info("mail is: " + mail);
        mailService.sendSimpleMail(mail, subject, content);
        return mailEntity;
    }
}
