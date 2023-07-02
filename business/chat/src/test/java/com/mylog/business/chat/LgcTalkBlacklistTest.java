package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.mapper.LgcTalkBlacklistMapper;
import com.mylog.business.chat.dal.mapper.LgcTalkSessionMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
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

/**
 * @Classname HBaseTest
 * @Description HBaseTest
 * @Date 5/24/2023 10:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LgcTalkBlacklistTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LgcTalkBlacklistTest.class);

    @Resource
    private LgcTalkBlacklistMapper blacklistMapper;

    @Test
    public void testAddBlacklist() {
        BlacklistInsertModel insertModel = new BlacklistInsertModel();
        insertModel.setBlockReason("你猜");
        insertModel.setBlockUserId(1);
        insertModel.setBlockedUserId(2);
        Integer integer = blacklistMapper.addBlacklist(insertModel);
        logger.info("entity {} insert ok : {}", insertModel, integer > 0);
    }


}
