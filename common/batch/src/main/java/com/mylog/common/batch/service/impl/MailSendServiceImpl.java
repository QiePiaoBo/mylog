//package com.mylog.common.batch.service.impl;
//
//import com.mylog.common.batch.service.IMailSendService;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author Dylan
// * @Date : Created in 11:32 2020/6/23
// * @Description : 批量邮件发送job调用
// */
//@Service
//public class MailSendServiceImpl implements IMailSendService {
//
//    @Resource
//    @Qualifier("adminMailSendJob")
//    Job adminMailSendJob;
//
//    @Resource
//    @Qualifier("userMailSendJob")
//    Job userMailSendJob;
//
//    @Resource
//    JobLauncher jobLauncher;
//
//    @Override
//    public String adminSendMail(){
//        String mailResult = "adminMailResult";
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timeStamp", System.currentTimeMillis())
//                    .addString("Date", new SimpleDateFormat("yyyyMMdd").format(new Date()))
//                    .toJobParameters();
//            JobExecution jobExecution = jobLauncher.run(adminMailSendJob, jobParameters);
//            mailResult = jobExecution.getJobInstance().getJobName();
//        }catch (Exception e){
//            e.fillInStackTrace();
//        }
//
//        return mailResult;
//    }
//
//    @Override
//    public String userSendMail(){
//        String mailResult = "userMailResult";
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timeStamp", System.currentTimeMillis())
//                    .addString("Date", new SimpleDateFormat("yyyyMMdd").format(new Date()))
//                    .toJobParameters();
//            JobExecution jobExecution = jobLauncher.run(userMailSendJob, jobParameters);
//            mailResult = jobExecution.getJobInstance().getJobName();
//        }catch (Exception e){
//            e.fillInStackTrace();
//        }
//        return mailResult;
//    }
//}
