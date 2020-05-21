package com.mylog.common.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDbService {

    @Autowired
    SimpleJobLauncher personDbLauncher;

    @Autowired
    Job personDbJob;

    public void readPerson() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time",
                System.currentTimeMillis()).toJobParameters();

        personDbLauncher.run(personDbJob, jobParameters);

    }



}
