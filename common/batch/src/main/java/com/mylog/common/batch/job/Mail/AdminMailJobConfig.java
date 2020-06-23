package com.mylog.common.batch.job.Mail;


import com.mylog.common.batch.model.entity.MailEntity;
import com.mylog.common.batch.model.rowmapper.MailRowMapper;
import com.mylog.common.batch.processors.AdminMailSendProcessor;
import com.mylog.common.batch.writer.CommonFileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 批量邮件发送配置中心
 * @author Dylan
 */
@Slf4j
@Component
public class AdminMailJobConfig {

    @Autowired
    @Qualifier("licDataSource")
    DataSource licDataSource;

    private String mailPath = "E:\\Files\\mylog\\mail";
    /**
     * 邮件读取器
     * @return
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader<MailEntity> adminMailReader(){
        JdbcPagingItemReader<MailEntity> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(licDataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new MailRowMapper());
        // 查询构造器
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, username, usergroup, phone, mail");
        queryProvider.setFromClause("from user");
        queryProvider.setWhereClause("usergroup <= 1");
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);
        return reader;
    }

    /**
     * 邮件发送复合数据处理器
     * @return
     */
//    @Bean
//    public ItemProcessor<MailEntity, MailEntity> mailProcessor(){
//        CompositeItemProcessor<MailEntity, MailEntity> processor = new CompositeItemProcessor<>();
//        List<ItemProcessor<MailEntity, MailEntity>> list = new ArrayList<>();
//        list.add(mailSendProcessor);
//        processor.setDelegates(list);
//        return processor;
//    }
    /**
     * 普通数据处理器
     */
    @Bean
    public ItemProcessor<MailEntity, MailEntity> adminMailProcessor(){
        return new AdminMailSendProcessor();
    }


    /**
     * 邮件书写器
     * @return
     */
    @Bean
    public FlatFileItemWriter<MailEntity> adminMailWriter(){
        return new CommonFileWriter<>(MailEntity.class);
    }

}
