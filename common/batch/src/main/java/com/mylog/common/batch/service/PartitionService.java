package com.mylog.common.batch.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartitionService {

    @Autowired
    private Job partitionJob;

    @Autowired
    private JobLauncher jobLauncher;

    public void partitionJob() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time",
                System.currentTimeMillis()).toJobParameters();

        JobExecution jobExecution =  jobLauncher.run(partitionJob, jobParameters);
        Long time = jobExecution.getEndTime().getTime()-jobExecution.getStartTime().getTime();
        System.out.println("分区批处理执行时间：" + time + "ms");
    }



}
