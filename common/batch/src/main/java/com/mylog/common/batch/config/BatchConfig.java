package com.mylog.common.batch.config;

import com.mylog.common.batch.listener.JobListener;
import com.mylog.common.batch.listener.StepListener;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 批量
 * @author Dylan
 */
@Component
public class BatchConfig {

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
     * @param transactionManager
     * @return
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, DataSourceTransactionManager transactionManager) throws Exception {

        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
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
