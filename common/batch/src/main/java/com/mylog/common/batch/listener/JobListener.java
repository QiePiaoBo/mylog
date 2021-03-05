package com.mylog.common.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 通用Job监听器
 * @author Dylan
 */
@Component
public class JobListener implements JobExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(JobListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job " + jobExecution.getJobInstance().getJobName() + " is starting...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Job " + jobExecution.getJobInstance().getJobName() + " is stopped.");
    }
}
