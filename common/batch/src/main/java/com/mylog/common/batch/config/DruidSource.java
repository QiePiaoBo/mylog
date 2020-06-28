package com.mylog.common.batch.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mylog.common.batch.datainfo.BlogDataInfo;
import com.mylog.common.batch.datainfo.LicenceDataInfo;
import com.mylog.common.batch.datainfo.TestDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 数据源配置
 * @author Dylan
 */
@Configuration
public class DruidSource {

    @Resource
    DataSource batchDataSource;

    @Resource
    DataSource licDataSource;

    @Resource
    DataSource blogDataSource;


    /**
     * 批量jdbcTemplate
     * @return
     */
//    @Bean
//    public JdbcTemplate batchJdbcTemplate(){
//        JdbcTemplate jdbcTemplate =  new JdbcTemplate();
//        jdbcTemplate.setDataSource(batchDataSource);
//        return jdbcTemplate;
//    }


    /**
     * licJdbcTemplate
     * @return
     */
//    @Bean
//    public JdbcTemplate licJdbcTemplate(){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(licDataSource);
//        return jdbcTemplate;
//    }


//    @Bean
//    public JdbcTemplate blogJdbcTemplate(){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(blogDataSource);
//        return jdbcTemplate;
//    }



}
