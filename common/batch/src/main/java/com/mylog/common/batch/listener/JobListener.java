package com.mylog.common.batch.listener;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 通用Job监听器
 * @author Dylan
 */
@Component
public class JobListener implements JobExecutionListener {
    private static final MyLogger logger = MyLoggerFactory.getLogger(JobListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job " + jobExecution.getJobInstance().getJobName() + " is starting...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Job " + jobExecution.getJobInstance().getJobName() + " is stopped.");
    }
}
