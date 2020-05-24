package com.mylog.common.batch.tasklets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 *这是一个汇聚任务
 *从处理结束后的汇聚 其中不做处理
 */
@Slf4j
public class DummyTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
            throws Exception {
        System.out.println("Dummy Tasklet called.");
        return RepeatStatus.FINISHED;
    }
}