package com.mylog.common.batch.model.rowmapper;

import com.mylog.common.batch.model.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dylan
 * @Date : Created in 9:02 2020/10/9
 * @Description :
 * @Function :
 */
public class MailSendRowMapper implements RowMapper<MailEntity> {
    private static final Logger logger = LoggerFactory.getLogger(MailSendRowMapper.class);


    @Override
    public MailEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new MailEntity(
                resultSet.getInt("id"),
                resultSet.getInt("usergroup"),
                resultSet.getString("userName"),
                resultSet.getString("phone"),
                resultSet.getString("mail")
                );
    }
}
