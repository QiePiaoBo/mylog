package com.mylog.common.batch.model.rowmapper;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.common.batch.model.entity.MailEntity;
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
    private static final MyLogger logger = MyLoggerFactory.getLogger(MailSendRowMapper.class);


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
