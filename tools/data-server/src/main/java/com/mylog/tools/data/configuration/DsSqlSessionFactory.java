package com.mylog.tools.data.configuration;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Dylan
 * @Date : Created in 13:50 2020/9/28
 * @Description :
 * @Function :
 */
public class DsSqlSessionFactory extends SqlSessionDaoSupport {
    private static final Logger logger = LoggerFactory.getLogger(DsSqlSessionFactory.class);

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return super.getSqlSessionTemplate();
    }
}
