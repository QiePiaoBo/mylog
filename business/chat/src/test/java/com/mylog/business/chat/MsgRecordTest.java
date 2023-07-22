package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.MsgRecordPartitionEnum;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.MsgQueryModel;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import com.mylog.business.chat.service.MsgRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Classname HBaseTest
 * @Description HBaseTest
 * @Date 5/24/2023 10:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MsgRecordTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(MsgRecordTest.class);

    @Resource
    private MsgRecordMapper msgRecordMapper;


    @Resource
    private MsgRecordService msgRecordService;

    @Test
    public void testMsgRecord() {
        testBatchInsertMsg();
        testQueryMsgRecords();
    }


    @Test
    public void testQueryMsgRecords() {
        LocalDateTime now = LocalDateTime.now();
        MsgQueryModel msgQueryModel = new MsgQueryModel();
        msgQueryModel.setPartition(MsgRecordPartitionEnum.getPartition(now).getPartitionName());
        List<MsgRecordVO> msgRecordEntities = msgRecordService.getMsgRecordForClient(msgQueryModel);
        msgRecordEntities.sort(Comparator.comparing(MsgRecordVO::getMsgId).reversed());
        logger.info("res: {}", msgRecordEntities);
    }

    @Test
    public void testBatchInsertMsg() {
        MsgInsertModel insertModel1 = new MsgInsertModel();
        insertModel1.setFromId(1);
        insertModel1.setToId(2);
        insertModel1.setSessionId(1111);
        insertModel1.setMsgType(1);
        insertModel1.setMsgContent("坤坤");
        MsgInsertModel insertModel2 = new MsgInsertModel();
        insertModel2.setFromId(2);
        insertModel2.setToId(1);
        insertModel2.setMsgContent("小黑子");
        insertModel2.setMsgType(1);
        insertModel2.setSessionId(2222);
        boolean b = msgRecordService.msgRecordBatchInsert(Arrays.asList(insertModel1, insertModel2));
        logger.info("res: {}", b);
    }

}
