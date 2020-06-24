package com.mylog.common.batch.job.Mail;

import com.mylog.common.batch.listener.JobListener;
import com.mylog.common.batch.model.entity.MailEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 批量邮件发送注册 job
 * @author Dylan
 */
@Configuration
public class UserMailJob {

    @Autowired
    StepListener stepListener;

    @Autowired
    JobListener jobListener;

    @Bean
    public Job userMailSendJob(JobBuilderFactory jobs, Step userMailSendStep){
        return jobs.get("mailSendJob")
                .listener(jobListener)
                .incrementer(new RunIdIncrementer())
                .start(userMailSendStep)
                .build();
    }

    @Bean
    public Step userMailSendStep(StepBuilderFactory stepBuilderFactory,
                             JdbcPagingItemReader<MailEntity> userMailReader,
                             ItemProcessor<MailEntity, MailEntity> userMailProcessor,
                             FlatFileItemWriter<MailEntity> userMailWriter){
        return stepBuilderFactory.get("mailSendStep")
                .listener(stepListener)
                .<MailEntity, MailEntity>chunk(10)
                .reader(userMailReader)
                .processor(userMailProcessor)
                .writer(userMailWriter)
                .build();
    }


}