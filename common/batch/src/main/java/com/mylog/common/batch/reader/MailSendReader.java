package com.mylog.common.batch.reader;

import com.mylog.common.batch.model.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Dylan
 * @Date : Created in 11:16 2020/9/28
 * @Description :
 * @Function :
 */
public class MailSendReader implements ItemReader<MailEntity> {
    private static final Logger logger = LoggerFactory.getLogger(MailSendReader.class);

    @Autowired
    JdbcTemplate licJdbcTemplate;

    private String userType;
    private String sql;

    @Override
    public MailEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        try {
            if (null == userType || "".equals(userType)){
                logger.error("Empty userType for this job");
                throw new Exception();
            }
            if ("admin".equals(userType)){
                sql = "select username, usergroup, phone, mail from user where usergroup < 1;";
            }else if ("user".equals(userType)){
                sql = "select username, usergroup, phone, mail from user where usergroup >= 1;";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        MailEntity mailEntity = licJdbcTemplate.queryForObject(sql, MailEntity.class);
        return mailEntity;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
