package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.MsgRecordPartitionEnum;
import com.mylog.business.chat.dal.entity.TeamEntity;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.dal.mapper.TeamMapper;
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
public class TeamTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(TeamTest.class);

    @Resource
    private TeamMapper teamMapper;


    @Test
    public void testGetTeamByName() {
        TeamEntity teamByTeamName = teamMapper.getTeamByTeamName("515");
        logger.info("res: {}", teamByTeamName);
    }

}
