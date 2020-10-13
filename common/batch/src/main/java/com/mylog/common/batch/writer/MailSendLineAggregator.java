package com.mylog.common.batch.writer;

import com.mylog.common.batch.model.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * @author Dylan
 * @Date : Created in 15:11 2020/9/28
 * @Description :
 * @Function :
 */
public class MailSendLineAggregator extends DelimitedLineAggregator<MailEntity> {
    private static final Logger logger = LoggerFactory.getLogger(MailSendLineAggregator.class);

    @Override
    public String aggregate(MailEntity mailEntity) {
        return super.aggregate(mailEntity);
    }
}
