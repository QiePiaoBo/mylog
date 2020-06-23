package com.mylog.common.batch.config;

import com.mylog.common.batch.listener.JobListener;
import com.mylog.common.batch.listener.StepListener;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 批量总配置中心
 * @author Dylan
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JdbcTemplate batchJdbcTemplate;


    /**
     * 注册 事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


    /**
     * 注册 jobRepository 数据库存取
     * @param dataSource
     * @return
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource) throws Exception {

        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager(dataSource));
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * 注册 jobLauncher 执行器
     * @param jobRepository
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }


    /**
     * 注册 job监听器
     * @return
     */
    @Bean
    public JobExecutionListener jobExecutionListener(){
        return new JobListener();
    }


    /**
     * 注册 step监听器
     * @return
     */
    @Bean
    public StepExecutionListener stepExecutionListener(){
        return new StepListener();
    }

}
