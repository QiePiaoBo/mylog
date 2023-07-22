package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.mapper.BlacklistMapper;
import com.mylog.business.chat.dal.mapper.ConfettiMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import com.mylog.business.chat.model.ConfettiInsertModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Classname HBaseTest
 * @Description HBaseTest
 * @Date 5/24/2023 10:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LgcConfettiTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LgcConfettiTest.class);

    @Resource
    private ConfettiMapper confettiMapper;

    @Test
    public void testAddBlacklist() {
        ConfettiInsertModel insertModel = new ConfettiInsertModel();
        insertModel.setUserId(1);
        insertModel.setContent("This is content.");
        insertModel.setTitle("THIS IS TITLE");
        Integer integer = confettiMapper.addConfetti(insertModel);
        logger.info("entity {} insert ok : {}", insertModel, integer > 0);
    }


}
