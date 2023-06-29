package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.entity.MsgRecordPartitionEnum;
import com.mylog.business.chat.dal.mapper.LgcTalkSessionMapper;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.MsgQueryModel;
import com.mylog.business.chat.model.SessionInsertModel;
import com.mylog.business.chat.model.SessionQueryModel;
import com.mylog.business.chat.service.MsgRecordService;
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
public class LgcTalkSessionTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LgcTalkSessionTest.class);

    @Resource
    private LgcTalkSessionMapper lgcTalkSessionMapper;


    @Test
    public void testSession() {
        testInsertSession();
        testQuerySession();
    }

    @Test
    public void testInsertSession() {
        SessionInsertModel insert = new SessionInsertModel();
        insert.setSenderId(1);
        insert.setRecipientId(2);

        Integer integer = lgcTalkSessionMapper.insertSession(insert);
        logger.info("insert: {}", integer);


        SessionInsertModel insert1 = new SessionInsertModel();
        insert1.setSenderId(1);
        insert1.setRecipientId(2);
        SessionInsertModel insert2 = new SessionInsertModel();
        insert2.setTalkTeamId(11);

        Integer integer1 = lgcTalkSessionMapper.batchInsertSession(Arrays.asList(insert1, insert2));
        logger.info("insert batch : {}", integer1);
    }

    @Test
    public void testQuerySession() {
        SessionQueryModel query = new SessionQueryModel();
//        query.setSenderId(1);
//        query.setRecipientId(2);
//        query.setTalkTeamId(1);
//        query.setSessionId(2);
        List<LgcTalkSessionEntity> entities = lgcTalkSessionMapper.querySessions(query);
        logger.info("res: {}", entities);
    }




}
