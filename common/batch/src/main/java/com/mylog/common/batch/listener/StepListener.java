package com.mylog.common.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 通用step监听器
 * @author Dylan
 */
@Slf4j
@Component
public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info(stepExecution.getStepName() + " is starting...");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info(stepExecution.getStepName() + " is stopped.");
        return null;
    }
}
