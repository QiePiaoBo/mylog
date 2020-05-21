package com.mylog.common.batch.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.username}")
    private String sender;


    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMailMessage(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
        }catch (Exception e){
            log.info("简单邮件发送异常");
        }
    }

    /**
     * 发送可带附件文件的邮件（html is ok）
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendMimeMessage(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

//            // 文件附件
//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = file.getFilename();
//            helper.addAttachment(fileName, file);

            mailSender.send(message);

        }catch (MessagingException e){
            log.info("文件路径为：" + filePath);
            log.info("发送带附件的MimeMessage时发生异常！", e);
        }
    }


    /**
     * 发送带静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscIdMap
     */
    public void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
                FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
                helper.addInline(entry.getKey(), file);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送带静态文件的MimeMessge时发生异常！", e);
        }
    }
}
