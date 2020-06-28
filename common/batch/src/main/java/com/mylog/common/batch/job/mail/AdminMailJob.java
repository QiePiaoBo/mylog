package com.mylog.common.batch.job.mail;

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

import javax.annotation.Resource;

/**
 * 批量邮件发送注册 job
 * @author Dylan
 */
@Configuration
public class AdminMailJob {

    @Resource
    StepListener stepListener;

    @Resource
    JobListener jobListener;

    @Bean
    public Job adminMailSendJob(JobBuilderFactory jobs, Step adminMailSendStep){
        return jobs.get("adminMailSendJob")
                .listener(jobListener)
                .incrementer(new RunIdIncrementer())
                .start(adminMailSendStep)
                .build();
    }

    @Bean
    public Step adminMailSendStep(StepBuilderFactory stepBuilderFactory,
                             JdbcPagingItemReader<MailEntity> adminMailReader,
                             ItemProcessor<MailEntity, MailEntity> adminMailProcessor,
                             FlatFileItemWriter<MailEntity> adminMailWriter){
        return stepBuilderFactory.get("mailSendStep")
                .listener(stepListener)
                .<MailEntity, MailEntity>chunk(10)
                .reader(adminMailReader)
                .processor(adminMailProcessor)
                .writer(adminMailWriter)
                .build();
    }
}
