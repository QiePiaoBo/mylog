package com.mylog.common.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job " + jobExecution.getJobInstance().getJobName() + " is starting...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job " + jobExecution.getJobInstance().getJobName() + " is stopped.");
    }
}
