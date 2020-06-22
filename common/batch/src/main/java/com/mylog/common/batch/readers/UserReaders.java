package com.mylog.common.batch.readers;

import com.mylog.common.batch.model.UserEntity;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.support.ListPreparedStatementSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserReaders{

    @Autowired
    DataSource licDataSource;

    @Bean
    public JdbcCursorItemReader userMailReader(){

        JdbcCursorItemReader jdbcCursorItemReader = new JdbcCursorItemReader();

        // sql语句
        String sql = "select mail from user;";
        // 参数传递器
        ListPreparedStatementSetter listPreparedStatementSetter = new ListPreparedStatementSetter();
        // 行解析
        BeanPropertyRowMapper propertyRowMapper = new BeanPropertyRowMapper();
        propertyRowMapper.setMappedClass(UserEntity.class);
        // 设置属性
        jdbcCursorItemReader.setDataSource(licDataSource);
        jdbcCursorItemReader.setSql(sql);
        jdbcCursorItemReader.setPreparedStatementSetter(listPreparedStatementSetter);
        jdbcCursorItemReader.setRowMapper(propertyRowMapper);

        return jdbcCursorItemReader;
    }




}
