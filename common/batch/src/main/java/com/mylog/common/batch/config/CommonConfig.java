package com.mylog.common.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class CommonConfig {

    /**
     * 注册 jdbcTemplate 用于操作数据库
     * @param licDataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource licDataSource){
        JdbcTemplate jdbcTemplate =  new JdbcTemplate();
        jdbcTemplate.setDataSource(licDataSource);
        return jdbcTemplate;
    }

}
