package com.mylog.common.batch.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mylog.common.batch.datainfo.BlogDataInfo;
import com.mylog.common.batch.datainfo.LicenceDataInfo;
import com.mylog.common.batch.datainfo.TestDataInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 数据源配置
 * @author Dylan
 */
@Configuration
public class DruidSource {

    /**
     * 批量数据源
     */
    @Bean
    @Primary
    public DataSource batchDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(TestDataInfo.URL.getValue());
        dataSource.setUsername(TestDataInfo.USER_NAME.getValue());
        dataSource.setPassword(TestDataInfo.PASSWORD.getValue());
        dataSource.setDriverClassName(TestDataInfo.DRIVER_CLASS_NAME.getValue());
        return dataSource;
    }

    /**
     * 批量jdbcTemplate
     * @return
     */
    @Bean
    public JdbcTemplate batchJdbcTemplate(){
        JdbcTemplate jdbcTemplate =  new JdbcTemplate();
        jdbcTemplate.setDataSource(batchDataSource());
        return jdbcTemplate;
    }


    /**
     * licence数据源
     * @return
     */
    @Bean
    public DataSource licDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(LicenceDataInfo.URL.getValue());
        dataSource.setDriverClassName(LicenceDataInfo.DRIVER_CLASS_NAME.getValue());
        dataSource.setUsername(LicenceDataInfo.USER_NAME.getValue());
        dataSource.setPassword(LicenceDataInfo.PASSWORD.getValue());
        return dataSource;
    }

    /**
     * licJdbcTemplate
     * @return
     */
    @Bean
    public JdbcTemplate licJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(licDataSource());
        return jdbcTemplate;
    }

    /**
     * blog数据源
     * @return
     */
    @Bean
    public DataSource blogDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(BlogDataInfo.URL.getValue());
        dataSource.setDriverClassName(BlogDataInfo.DRIVER_CLASS_NAME.getValue());
        dataSource.setUsername(BlogDataInfo.USER_NAME.getValue());
        dataSource.setPassword(BlogDataInfo.PASSWORD.getValue());
        return dataSource;
    }

    @Bean
    public JdbcTemplate blogJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(blogDataSource());
        return jdbcTemplate;
    }



}
