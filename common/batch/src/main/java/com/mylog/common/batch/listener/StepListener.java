package com.mylog.common.batch.listener;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 通用step监听器
 * @author Dylan
 */
@Component
public class StepListener implements StepExecutionListener {
    private static final MyLogger logger = MyLoggerFactory.getLogger(StepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info(stepExecution.getStepName() + " is starting...");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info(stepExecution.getStepName() + " is stopped.");
        return null;
    }
}
