package com.mylog.common.batch.writer;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.common.batch.model.entity.MailEntity;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

/**
 * @author Dylan
 * @Date : Created in 15:11 2020/9/28
 * @Description :
 * @Function :
 */
public class MailSendLineAggregator extends DelimitedLineAggregator<MailEntity> {
    private static final MyLogger logger = MyLoggerFactory.getLogger(MailSendLineAggregator.class);

    @Override
    public String aggregate(MailEntity mailEntity) {
        return super.aggregate(mailEntity);
    }
}
