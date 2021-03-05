package com.mylog.common.batch.processors;

import com.mylog.common.batch.common.CommonSkipException;
import com.mylog.common.batch.model.entity.MailEntity;
import com.mylog.common.batch.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.SendFailedException;

/**
 * 批量邮件发送processor
 * @author Dylan
 */
public class MailSendProcessor extends JobExecutionListenerSupport implements ItemProcessor<MailEntity, MailEntity> {
    private static final Logger logger = LoggerFactory.getLogger(MailSendProcessor.class);
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
        if (mailEntity.getUsername().equals("Dylan")){
            logger.info("Sending mail to Dylan.");
        }
        String mail = mailEntity.getMail();
        logger.info("mail is: " + mail);
        try {
            mailService.sendSimpleMail(mail, subject, content);
        }catch (Exception e){
        }
        return mailEntity;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (null == jobExecution.getJobParameters().getString("subject") || null == jobExecution.getJobParameters().getString("content")){
            logger.error("subject or content is null for this job.");
        }
        setSubject(jobExecution.getJobParameters().getString("subject"));
        setContent(jobExecution.getJobParameters().getString("content"));
        super.afterJob(jobExecution);
    }
}
