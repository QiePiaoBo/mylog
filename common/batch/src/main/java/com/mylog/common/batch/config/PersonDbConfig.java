package com.mylog.common.batch.config;

import com.mylog.common.batch.itemProcessors.PersonDbProcessor;
import com.mylog.common.batch.listeners.PersonDbListener;
import com.mylog.common.batch.models.Person;
import com.mylog.common.batch.rowMapper.PersonRowMapper;
import com.mylog.common.batch.service.MailService;
import com.mylog.common.batch.validators.PersonDbValidator;
import com.mylog.common.batch.writers.PersonDbWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册spring batch 的各个部分
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@Import(DruidDBConfig.class)
public class PersonDbConfig {

    @Autowired
    MailService mailService;

    @Autowired
    DruidDBConfig druidDBConfig;

    /**
     * 注册ItemReader
     * @return
     */
    @Bean
    public JdbcPagingItemReader<Person> personDbReader(){
        JdbcPagingItemReader<Person> reader = new JdbcPagingItemReader<>();
        // 设置数据源
        reader.setDataSource(druidDBConfig.dataSource());
        // 设置一次最大读取条数
        reader.setFetchSize(100);
        // 把数据库的每条数据映射到Person实体类
        reader.setRowMapper(new PersonRowMapper());
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        // 设置要查询的列
        queryProvider.setSelectClause("id, name, age, gender");
        // 设置要查询的表
        queryProvider.setFromClause("person");
        // 定义一个集合用于存放排序列
        Map<String, Order> sortKeys = new HashMap<String, Order>();
        // 按照升序 排列
        sortKeys.put("id", Order.ASCENDING);
        // 设置排序列
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);
        return reader;
    }

    /**
     * 注册ItemProcessor
     * @return
     * @throws Exception
     */
    @Bean
    public ItemProcessor<Person, Person> personDbProcessor() throws Exception{
        // 设置处理器
        PersonDbProcessor personDbProcessor = new PersonDbProcessor();
        // 为处理器设置校验器
        personDbProcessor.setValidator(personDbValidator());
        return personDbProcessor;
    }

    /**
     * 注册校验器
     * @return
     */
    @Bean
    public PersonDbValidator personDbValidator(){
        return new PersonDbValidator<Person>();
    }

    /**
     * 注册itemWriter
     * @return
     */
    @Bean
    public PersonDbWriter personDbWriter(){
        return new PersonDbWriter();
    }

    /**
     * JobRepository定义，设置数据库 注册job容器
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository personDbRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * jobLauncher定义
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher personDbLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        // 设置jobRepository
        jobLauncher.setJobRepository(personDbRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    /**
     * 定义job
     * @param jobs
     * @param personDbStep
     * @return
     */
    @Bean
    public Job personDbJob(JobBuilderFactory jobs, Step personDbStep){

        return jobs.get("personExportJob")
                .incrementer(new RunIdIncrementer())
                .flow(personDbStep)
                .end()
                .listener(personDbListener())
                .build();
    }

    /**
     * 注册监听器
     * @return
     */
    @Bean
    public PersonDbListener personDbListener(){
        return new PersonDbListener();
    }

    @Bean
    public Step personDbStep(StepBuilderFactory stepBuilderFactory,
                             ItemReader<Person> personDbReader,
                             ItemProcessor<Person, Person> personDbProcessor,
                             ItemWriter<Person> personDbWriter){

        return stepBuilderFactory
                .get("personDbStep")
                .<Person, Person>chunk(65000)
                .reader(personDbReader)
                .processor(personDbProcessor)
                .writer(personDbWriter)
                .build();
    }

/**
 * 测试
 *
 */
    @Bean
    public PartitionHandler partitionHandler(Step personDbStep) {
        TaskExecutorPartitionHandler retVal = new TaskExecutorPartitionHandler();
//        retVal.setTaskExecutor(taskExecutor());
        retVal.setStep(personDbStep);
        retVal.setGridSize(10);
        return retVal;
    }

}
