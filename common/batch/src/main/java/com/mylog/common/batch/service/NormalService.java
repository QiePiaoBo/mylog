package com.mylog.common.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NormalService {

    @Autowired
    private SimpleJobLauncher userDbLauncher;

    @Autowired
    private Job userDbJob;

    public void userDbService() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = userDbLauncher.run(userDbJob, jobParameters);
        Long time = jobExecution.getEndTime().getTime()-jobExecution.getStartTime().getTime();
        System.out.println("普通批处理执行时间：" + time + "ms");
    }


}
