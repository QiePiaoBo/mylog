package com.mylog.common.batch.config;

import com.mylog.common.batch.itemProcessors.PersonDbProcessor;
import com.mylog.common.batch.itemProcessors.UserProcessor;
import com.mylog.common.batch.models.Person;
import com.mylog.common.batch.models.User;
import com.mylog.common.batch.rowMapper.PersonRowMapper;
import com.mylog.common.batch.rowMapper.UserRowMapper;
import com.mylog.common.batch.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableBatchProcessing
@Import(DruidDBConfig.class)
public class UserDbConfig {
    @Autowired
    private MailService mailService;

    @Autowired
    DruidDBConfig druidDBConfig;

    /**
     * 注册ItemReader
     * @return
     */
    @Bean
    public JdbcPagingItemReader<User> userDbReader(){
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
        // 设置数据源
        reader.setDataSource(druidDBConfig.dataSource());
        // 设置一次最大读取条数
        reader.setFetchSize(100);
        // 把数据库的每条数据映射到Person实体类
        reader.setRowMapper(new UserRowMapper());
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        // 设置要查询的列
        queryProvider.setSelectClause("id, age, password, username");
        // 设置要查询的表
        queryProvider.setFromClause("user");
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
    public ItemProcessor<User, User> userDbProcessor(){
        // 设置处理器
        UserProcessor userDbProcessor = new UserProcessor();

        return userDbProcessor;
    }

    /**
     * 注册ItemWriter
     *
     */
    @Bean
    public ItemWriter<User> userDbWriter(){
        FlatFileItemWriter<User> reader = new FlatFileItemWriter<>();
        reader.setResource(new FileSystemResource(
                "common/batch/csv/users.csv"));
        //reader.setAppendAllowed(false);
        reader.setLineAggregator(new DelimitedLineAggregator<User>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
                setNames(new String[]{"id", "username", "password", "age"});
            }});
        }});
        return reader;
    }

    /**
     * 注册step
     *
     */
    @Bean
    public Step userDbStep(
            StepBuilderFactory stepBuilderFactory,
            ItemReader<User> userDbReader,
            ItemProcessor<User, User> userDbProcessor,
            ItemWriter<User> userDbWriter){
        return stepBuilderFactory.get("userDbStep")
                .<User, User> chunk(65000)
                .reader(userDbReader)
                .processor(userDbProcessor)
                .writer(userDbWriter)
                .build();
    }

    /**
     * 注册job
     */

    @Bean
    public Job userDbJob(JobBuilderFactory jobs, Step userDbStep){
        return jobs.get("userDbJob")
                .incrementer(new RunIdIncrementer())
                .flow(userDbStep)
                .end()
                .build();
    }

    /**
     * 注册jobRepository
     */
    @Bean
    public JobRepository userDbRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * 注册jogLuncher
     */
    @Bean
    public SimpleJobLauncher userDbLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        // 设置jobRepository
        jobLauncher.setJobRepository(userDbRepository(dataSource, transactionManager));
        return jobLauncher;
    }

}
