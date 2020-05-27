package com.mylog.common.batch.jobs;

import com.mylog.common.batch.itemProcessors.UserProcessor;
import com.mylog.common.batch.models.User;
import com.mylog.common.batch.partitions.RangePartitioner;
import com.mylog.common.batch.tasklets.DummyTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableBatchProcessing
public class PartitionerJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job partitionJob() {
        return jobBuilderFactory.get("partitionJob").incrementer(new RunIdIncrementer())
                .start(masterStep()).next(step2()).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(dummyTask()).build();
    }

    @Bean
    public DummyTasklet dummyTask() {
        return new DummyTasklet();
    }

    /**
     * 主节点步骤
     * rangePartitioner对象是核心分区类，对每个分区后的从节点处理器具体业务为RangePartitioner类的内容
     * @return
     */
    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep").partitioner(slave().getName(), rangePartitioner())
                .partitionHandler(masterSlaveHandler()).build();
    }

    /**
     * 主步骤后面一个动作
     * masterSlaveHandler
     * 类中设置了分片大小为10个
     * 同时设置异步任务执行器taskExecutor()
     * @return
     */
    @Bean
    public PartitionHandler masterSlaveHandler() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(10);
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(slave());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

    /**
     * 定义用于重复作业的子step
     * @return
     */
    @Bean(name = "slave")
    public Step slave() {
        log.info("...........called slave .........");

        return stepBuilderFactory.get("slave").<User, User>chunk(100)
                .reader(slaveReader(null, null, null))
                .processor(slaveProcessor(null)).writer(slaveWriter(null, null)).build();
    }

    @Bean
    public RangePartitioner rangePartitioner() {
        return new RangePartitioner();
    }

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    @StepScope
    public UserProcessor slaveProcessor(@Value("#{stepExecutionContext[name]}") String name) {
        log.info("********called slave processor **********");
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.setThreadName(name);
        return userProcessor;
    }

    /**
     * [toId]和[name]值将被注入到ExecutionContext中的rangePartitioner
     * @param fromId
     * @param toId
     * @param name
     * @return
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader<User> slaveReader(
            @Value("#{stepExecutionContext[fromId]}") final String fromId,
            @Value("#{stepExecutionContext[toId]}") final String toId,
            @Value("#{stepExecutionContext[name]}") final String name) {
        log.info("slaveReader start " + fromId + " " + toId);
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setQueryProvider(queryProvider());
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("fromId", fromId);
        parameterValues.put("toId", toId);
        log.info("Parameter Value " + name + " " + parameterValues);
        reader.setParameterValues(parameterValues);
        reader.setPageSize(1000);
        reader.setRowMapper(new BeanPropertyRowMapper<User>() {{
            setMappedClass(User.class);
        }});
        log.info("slaveReader end " + fromId + " " + toId);
        return reader;
    }

    @Bean
    public PagingQueryProvider queryProvider() {
        log.info("queryProvider start ");
        SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("select id, username, password, age");
        provider.setFromClause("from user");
        provider.setWhereClause("where id >= :fromId and id <= :toId");
        provider.setSortKey("id");
        log.info("queryProvider end ");
        try {
            return provider.getObject();
        } catch (Exception e) {
            log.info("queryProvider exception ");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 每个线程将以不同的csv文件输出记录
     * 文件名格式为user.processed[fromId]-[toId].csv
     * @param fromId
     * @param toId
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemWriter<User> slaveWriter(
            @Value("#{stepExecutionContext[fromId]}") final String fromId,
            @Value("#{stepExecutionContext[toId]}") final String toId) {
        FlatFileItemWriter<User> reader = new FlatFileItemWriter<>();
        reader.setResource(new FileSystemResource(
                "common/batch/csv/outputs/users.processed" + fromId + "-" + toId + ".csv"));
        //reader.setAppendAllowed(false);
        reader.setLineAggregator(new DelimitedLineAggregator<User>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
                setNames(new String[]{"id", "username", "password", "age"});
            }});
        }});
        return reader;
    }
}
