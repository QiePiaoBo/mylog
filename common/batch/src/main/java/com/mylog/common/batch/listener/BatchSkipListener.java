package com.mylog.common.batch.listener;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
 * @author Dylan
 * @Date : Created in 8:58 2020/8/24
 * @Description : skipListener 可获取异常数据
 * @Function :
 */
public class BatchSkipListener extends StepExecutionListenerSupport implements SkipListener {
    private static final MyLogger logger = MyLoggerFactory.getLogger(BatchSkipListener.class);

    private StepExecution step;

    @Override
    public void onSkipInRead(Throwable throwable) {
        logger.error("批量读取处理过程异常处理{}", step.getJobExecutionId());
    }

    @Override
    public void onSkipInWrite(Object o, Throwable throwable) {
        logger.error("批量写处理过程异常处理{}","");
        this.exceptionHandMethod(o, throwable);
    }

    @Override
    public void onSkipInProcess(Object o, Throwable throwable) {
        logger.error("批量处理过程异常");
        this.exceptionHandMethod(o, throwable);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.step = stepExecution;
    }

    public void exceptionHandMethod(Object item, Throwable t){

    }
}
