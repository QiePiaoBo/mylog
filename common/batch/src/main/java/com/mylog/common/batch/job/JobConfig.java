package com.mylog.common.batch.job;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author Dylan
 * @Date : Created in 12:29 2020/9/28
 * @Description :
 * @Function :
 */
@Configuration
public class JobConfig {
    private static final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Autowired
    JobRepository batchJobRepository;

    @Autowired
    DataSourceTransactionManager batchTransactionManager;

    @Autowired
    DruidDataSource batchDataSource;

    @Autowired
    DruidDataSource blogDataSource;

    @Autowired
    DruidDataSource licDataSource;

    @Bean
    public JobBuilderFactory jobs(){
        return new JobBuilderFactory(batchJobRepository);
    }

    @Bean
    public StepBuilderFactory steps(){
        return new StepBuilderFactory(batchJobRepository, batchTransactionManager);
    }

    @Bean("batchJdbcTemplate")
    public JdbcTemplate batchJdbcTemplate(){
        return new JdbcTemplate(batchDataSource);
    }

    @Bean("licJdbcTemplate")
    public JdbcTemplate licJdbcTemplate(){
        return new JdbcTemplate(licDataSource);
    }

    @Bean("blogJdbcTemplate")
    public JdbcTemplate blogJdbcTemplate(){
        return new JdbcTemplate(blogDataSource);
    }
}
