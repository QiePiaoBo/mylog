package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.ConfettiEntity;
import com.mylog.business.chat.dal.mapper.BlacklistMapper;
import com.mylog.business.chat.dal.mapper.ConfettiMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.ConfettiMergeModel;
import com.mylog.business.chat.model.ConfettiQueryModel;
import com.mylog.business.chat.service.ConfettiService;
import com.mylog.tools.model.model.result.HttpResult;
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
public class LgcConfettiTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(LgcConfettiTest.class);

    @Resource
    private ConfettiMapper confettiMapper;

    @Resource
    private ConfettiService confettiService;

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

    @Test
    public void testGetConfettiOfIds() {
        List<ConfettiEntity> confettiOfIds = confettiMapper.getConfettiOfIds(Arrays.asList(18,19,20));
        logger.info("res: {}", confettiOfIds);
    }

    @Test
    public void testAddOrUpdate() {
        ConfettiEntity entity1 = new ConfettiEntity();
        entity1.setId(17);
        entity1.setUserId(4);
        entity1.setTitle("THIS IS TITLE ABOUT YOUR CONFETTI");
        entity1.setContent("THIS IS TITLE ABOUT YOUR CONFETTI");
        entity1.setDelFlag(0);
        ConfettiEntity entity2 = new ConfettiEntity();
        entity2.setId(15);
        entity2.setUserId(1);
        entity2.setTitle("THIS IS TITLE ABOUT YOUR CONFETTI");
        entity2.setContent("THIS IS TITLE ABOUT YOUR CONFETTI");
        entity2.setDelFlag(0);


        Integer integer = confettiMapper.addOrUpdateConfettiBatch(Arrays.asList(entity1, entity2));
        logger.info("res: {}", integer);
    }

    @Test
    public void testMerge() {
        ConfettiMergeModel model = new ConfettiMergeModel();
        model.setCurUserId(1);
        model.setSecretKey("logicer");
        model.setMergeFrom(17);
        model.setMergeTo(15);
        HttpResult httpResult = confettiService.mergeConfetti(model);
        logger.info("res: {}", httpResult);
    }

}
