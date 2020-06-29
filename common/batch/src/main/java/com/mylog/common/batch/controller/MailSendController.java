package com.mylog.common.batch.controller;

import com.mylog.common.batch.model.dto.MailContentDto;
import com.mylog.common.batch.service.impl.MailSendService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dylan
 * @Date : Created in 12:21 2020/6/23
 * @Description : java类
 */
@RestController
@RequestMapping("mail")
public class MailSendController {
    @Resource
    private MailSendService mailSendService;

    @RequestMapping("admin")
    public String adminMail(@RequestBody MailContentDto mailContentDto) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return mailSendService.mailSend(mailContentDto);
    }
}
