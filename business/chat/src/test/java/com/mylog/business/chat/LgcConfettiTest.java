package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.ConfettiEntity;
import com.mylog.business.chat.dal.mapper.BlacklistMapper;
import com.mylog.business.chat.dal.mapper.ConfettiMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.ConfettiQueryModel;
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

    @Test
    public void testGetConfettiForUser() {
        ConfettiQueryModel confettiQueryModel1 = new ConfettiQueryModel();
        confettiQueryModel1.setUserId(1);
        ConfettiQueryModel confettiQueryModel2 = new ConfettiQueryModel();
        confettiQueryModel2.setTitle("IS");
        List<ConfettiEntity> confettiForUser1 = confettiMapper.getConfettiForUser(confettiQueryModel1);
        List<ConfettiEntity> confettiForUser2 = confettiMapper.getConfettiForUser(confettiQueryModel2);
        logger.info("entity1 {} insert ok : {}", confettiForUser1, confettiForUser1.size() > 0);
        logger.info("entity2 {} insert ok : {}", confettiForUser2, confettiForUser2.size() > 0);
    }

}
