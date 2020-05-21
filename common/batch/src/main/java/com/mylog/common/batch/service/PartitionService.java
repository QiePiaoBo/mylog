package com.mylog.common.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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

        jobLauncher.run(partitionJob, jobParameters);
    }



}
