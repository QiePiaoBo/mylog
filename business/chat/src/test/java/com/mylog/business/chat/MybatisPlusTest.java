package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.dal.model.MsgQueryModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname HBaseTest
 * @Description HBaseTest
 * @Date 5/24/2023 10:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisPlusTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(MybatisPlusTest.class);

    @Resource
    private MsgRecordMapper msgRecordMapper;

    @Test
    public void testQueryMsgRecords() {
        MsgQueryModel msgQueryModel = new MsgQueryModel();
        msgQueryModel.setPartition("p202307");
        msgQueryModel.setToId(3);
        msgQueryModel.setFromId(1);
        List<MsgRecordEntity> msgRecordEntities = msgRecordMapper.queryMsgRecords(msgQueryModel);
        logger.info("res: {}", msgRecordEntities);
    }

}
