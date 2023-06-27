package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.entity.MsgRecordPartitionEnum;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.dal.model.MsgInsertModel;
import com.mylog.business.chat.dal.model.MsgQueryModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
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
        LocalDateTime now = LocalDateTime.now();
        MsgQueryModel msgQueryModel = new MsgQueryModel();
        msgQueryModel.setPartition(MsgRecordPartitionEnum.getPartition(now).getPartitionName());
//        msgQueryModel.setToId(3);
//        msgQueryModel.setFromId(1);
        List<MsgRecordEntity> msgRecordEntities = msgRecordMapper.queryMsgRecords(msgQueryModel);
        logger.info("res: {}", msgRecordEntities);
    }

    @Test
    public void testBatchInsert() {
        MsgInsertModel insertModel1 = new MsgInsertModel();
        insertModel1.setFromId(1);
        insertModel1.setToId(2);
        insertModel1.setMsgContent("你好");
        MsgInsertModel insertModel2 = new MsgInsertModel();
        insertModel2.setFromId(2);
        insertModel2.setToId(1);
        insertModel2.setMsgContent("你也好");
        Integer integer = msgRecordMapper.batchInsert(Arrays.asList(insertModel1, insertModel2));
        logger.info("res: {}", integer);
    }




}
