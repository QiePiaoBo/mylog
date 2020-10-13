package com.mylog.common.batch.service.impl;

import com.mylog.common.batch.model.dto.MailContentDto;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dylan
 * @Date : Created in 9:53 2020/6/29
 * @Description : job服务
 */
@Service
public class MailSendServiceImpl {

    @Resource
    private Job mailSendJob;

    @Resource
    private JobLauncher batchJobLauncher;

    public String mailSend(MailContentDto mailDto) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date());
        String fileHourStamp = new SimpleDateFormat("yyyyMMddhh").format(new Date());
        String fileSecondStamp = new SimpleDateFormat("mmss").format(new Date());
        String filePath = "/var/www/springcloud/data/";
        String mailSubject = "项目运行情况";
        String mailContent = "正常运行，时间：" + timeStamp;
        if (mailDto.getMailSubject()!=null){
            mailSubject = mailDto.getMailSubject();
        }
        if (mailDto.getMailContent() != null){
            mailContent = mailDto.getMailContent() + timeStamp;
        }
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")){
            filePath = "F:\\Files\\mylog\\mail\\";
        }
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("userType", mailDto.getUserType())
                .addString("filePath", filePath)
                .addString("subject", mailSubject)
                .addString("content", mailContent)
                .addString("fileHourStamp", fileHourStamp)
                .addString("fileSecondStamp", fileSecondStamp)
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = batchJobLauncher.run(mailSendJob, jobParameters);
        return jobExecution.getExitStatus().getExitCode();
    }



}
