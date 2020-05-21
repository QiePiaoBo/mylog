package com.mylog.common.batch.config;

import com.mylog.common.batch.itemProcessors.CvsItemProcessor;
import com.mylog.common.batch.listeners.CsvJobListener;
import com.mylog.common.batch.models.Person;
import com.mylog.common.batch.service.MailService;
import com.mylog.common.batch.validators.CsvBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.lang.management.PlatformLoggingMXBean;

/**
 * 注册spring batch的各个组成部分
 */
@Configuration
@EnableBatchProcessing
@Import(DruidDBConfig.class)
public class CsvBatchConfig {

    @Autowired
    MailService mailService;

    private Logger logger = LoggerFactory.getLogger(CsvBatchConfig.class);

    /**
     * ItemReader定义：读取文件数据+entity映射
     * @return
     */
    @Bean
    public ItemReader<Person> reader(){
        // 在itemreader中添加邮件发送服务
//        mailService.sendMimeMessage("1171357812@qq.com", "批量处理任务", "<h1>批量任务开始。。。</h1>","");

        // 使用FilterItemReader去读csv文件，一行就是一条数据
        FlatFileItemReader<Person> reader = new FlatFileItemReader();
        // 设置文件所在路径
        reader.setResource(new ClassPathResource("person.csv"));
        // entity与csv数据做映射
        reader.setLineMapper(new DefaultLineMapper<Person>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{"id","name","age","gender"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){
                    {
                        setTargetType(Person.class);
                    }
                });
            }
        });
        return reader;
    }

    /**
     * 注册ItemProcessor: 处理数据 + 校验数据
     * @return
     */
    @Bean
    public ItemProcessor<Person, Person> processor() throws Exception{
        CvsItemProcessor cvsItemProcessor = new CvsItemProcessor();
        // 设置校验器
        cvsItemProcessor.setValidator(csvBeanValidator());
        return cvsItemProcessor;
    }

    /**
     * 校验器，，，，，，
     * @return
     * @throws Exception
     */
    @Bean
    public CsvBeanValidator csvBeanValidator(){
        return new CsvBeanValidator<Person>();
    }

    /**
     * ItemWriter定义，指定datasource 设置批量插入sql语句，写入数据库
     * @param dataSource
     * @return
     */
    @Bean
    public ItemWriter<Person> writer(DataSource dataSource){
        // 使用jdbcBatchItemWriter写数据到数据库中
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        // 设置有参数的sql语句

        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into person(name, age , gender) values(:name, :age, :gender)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * JobRepository定义，设置数据库 注册job容器
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository cvsJobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
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
    public SimpleJobLauncher csvJobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
        throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        // 设置jobRepository
        jobLauncher.setJobRepository(cvsJobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    /**
     * 定义job
     * @param jobs
     * @param step
     * @return
     */
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step){
        Flow flow1 = new FlowBuilder<SimpleFlow>("asyncFlow1").start(step).build();

        return jobs.get("importCsvJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .split(new SimpleAsyncTaskExecutor())
                .add(flow1)
                .end()
                .listener(csvJobListener())
                .build();
    }

    /**
     * 注册job监听器
     * @return
     */
    @Bean
    public CsvJobListener csvJobListener(){
        return new CsvJobListener();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
                     ItemWriter<Person> writer, ItemProcessor<Person, Person> processor){
        return stepBuilderFactory
                .get("step")
                .<Person, Person>chunk(65000) // chunk的机制 每次读取一条数据，再处理一条数据，积累到一定的量再给writer写
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
