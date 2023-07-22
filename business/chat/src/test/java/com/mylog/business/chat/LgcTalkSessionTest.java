package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.SessionEntity;
import com.mylog.business.chat.dal.mapper.SessionMapper;
import com.mylog.business.chat.dal.mapper.UserMapper;
import com.mylog.business.chat.model.SessionInsertModel;
import com.mylog.business.chat.model.SessionQueryModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.business.chat.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private SessionMapper sessionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SessionService sessionService;


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

        Integer integer = sessionMapper.insertSession(insert);
        logger.info("insert: {}", integer);


        SessionInsertModel insert1 = new SessionInsertModel();
        insert1.setSenderId(1);
        insert1.setRecipientId(2);
        SessionInsertModel insert2 = new SessionInsertModel();
        insert2.setTalkTeamId(11);

        Integer integer1 = sessionMapper.batchInsertSession(Arrays.asList(insert1, insert2));
        logger.info("insert batch : {}", integer1);
    }

    @Test
    public void testQuerySession() {
        SessionQueryModel query = new SessionQueryModel();
//        query.setSenderId(1);
//        query.setRecipientId(2);
//        query.setTalkTeamId(1);
//        query.setSessionId(2);
        List<SessionEntity> entities = sessionMapper.querySessions(query);
        logger.info("res: {}", entities);
    }

    @Test
    public void testGetOrCreateSession() {
        String userName = "dylan";
        String talkWith = "lucifer";
        Map<String, Integer> userNameIdMap = sessionService.getUserNameIdMap(Arrays.asList(userName, talkWith));
        Integer sessionId = sessionService.getOrCreateSessionForUser(userNameIdMap.get(userName),userNameIdMap.get(talkWith));
        logger.info("sessionId: {}, userName: {}, talkWith: {}", sessionId, userName, talkWith);
    }

    @Test
    public void testGetUserNameId() {
        List<UserNameIdModel> userNameIds = userMapper.getUserNameId(Arrays.asList("Dylan", "Duke"));
        logger.info("res: {}", userNameIds);
    }




}
