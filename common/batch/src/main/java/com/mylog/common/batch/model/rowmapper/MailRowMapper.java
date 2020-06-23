package com.mylog.common.batch.model.rowmapper;

import com.mylog.common.batch.model.entity.MailEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dylan
 * @Date : Created in 12:59 2020/6/23
 * @Description : MailRowMapper
 */
public class MailRowMapper implements RowMapper<MailEntity> {
    @Override
    public MailEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new MailEntity(
                resultSet.getString("username"),
                resultSet.getInt("usergroup"),
                resultSet.getString("phone"),
                resultSet.getString("mail")
                );
    }
}
