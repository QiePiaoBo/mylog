package com.mylog.common.batch.job;

import com.alibaba.druid.pool.DruidDataSource;
import com.mylog.common.batch.listener.JobListener;
import com.mylog.common.batch.listener.StepListener;
import com.mylog.common.batch.model.entity.MailEntity;
import com.mylog.common.batch.model.rowmapper.MailSendRowMapper;
import com.mylog.common.batch.processors.MailSendProcessor;
import com.mylog.common.batch.writer.CommonFlatFileItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan
 * @Date : Created in 11:28 2020/9/28
 * @Description :
 * @Function :
 */
@Configuration
public class MailSendJobImpl {

    @Autowired
    JobBuilderFactory jobs;

    @Autowired
    StepBuilderFactory steps;

    @Autowired
    DruidDataSource licDataSource;

    /**
     * 定义邮件发送step
     * @return
     */
    @Bean("mailSendStep")
    public Step step(ItemReader<MailEntity> mailSendItemReader,
                     ItemProcessor<MailEntity, MailEntity> mailSendItemProcessor,
                     ItemWriter<MailEntity> mailSendItemWriter){
        Step step = steps.get("mailSendStep")
                .listener(mailSendStepListener())
                .<MailEntity, MailEntity>chunk(10)
                .reader(mailSendItemReader)
                .processor(mailSendItemProcessor)
                .writer(mailSendItemWriter)
                .build();
        return step;
    }

    /**
     * 定义邮件发送job
     * @return
     */
    @Bean("mailSendJob")
    public Job mailSendJob(Step mailSendStep){
        Job mailSendJob = jobs.get("mailSendJob")
                .listener(mailSendJobListener())
                .flow(mailSendStep)
                .end()
                .build();
        return mailSendJob;
    }

    /**
     * 邮件发送job监听器
     * @return
     */
    @Bean
    public JobListener mailSendJobListener(){
        return new JobListener();
    }

    /**
     * 邮件发送step监听器
     * @return
     */
    @Bean
    public StepListener mailSendStepListener(){
        return new StepListener();
    }

    /**
     * 邮件发送itemReader
     * @return
     */
    @Bean("mailSendItemReader")
    @StepScope
    public ItemReader<MailEntity> mailSendItemReader(@Value("#{jobParameters[userType]}") String userType) throws Exception {
        JdbcPagingItemReader<MailEntity> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(licDataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new MailSendRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, usergroup, username, phone, mail");
        queryProvider.setFromClause("from user");
        if (null == userType || "".equals(userType)){
            throw new Exception("Cannot get userType for itemReader");
        }
        if ("admin".equals(userType)){
            queryProvider.setWhereClause("usergroup <= 1");
        }else if ("user".equals(userType)){
            queryProvider.setWhereClause("usergroup > 1");
        }
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);
        return reader;
    }

    /**
     * 邮件发送itemProcessor
     * @return
     */
    @Bean("mailSendItemProcessor")
    @StepScope
    public ItemProcessor<MailEntity, MailEntity> mailSendItemProcessor(@Value("#{jobParameters[subject]}") String subject, @Value("#{jobParameters[content]}") String content){
        MailSendProcessor mailSendProcessor = new MailSendProcessor();
        mailSendProcessor.setSubject(subject);
        mailSendProcessor.setContent(content);
        return mailSendProcessor;
    }

    /**
     * 邮件发送itemWriter
     * @return
     */
    @Bean("mailSendItemWriter")
    public FlatFileItemWriter<MailEntity> mailSendItemWriter(){
        return new CommonFlatFileItemWriter<>(MailEntity.class);
    }

}
