package com.mylog.common.batch.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mylog.common.batch.datainfo.BlogDataInfo;
import com.mylog.common.batch.datainfo.LicenceDataInfo;
import com.mylog.common.batch.datainfo.TestDataInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@Configuration
public class DruidSource {

    /**
     * 测试用数据源
     */
    @Bean
    public DataSource testDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(TestDataInfo.URL.getValue());
        dataSource.setUsername(TestDataInfo.USER_NAME.getValue());
        dataSource.setPassword(TestDataInfo.PASSWORD.getValue());
        dataSource.setDriverClassName(TestDataInfo.DRIVER_CLASS_NAME.getValue());
        return dataSource;
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



}
