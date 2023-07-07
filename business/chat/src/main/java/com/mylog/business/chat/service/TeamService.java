package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.TeamEntity;
import com.mylog.business.chat.dal.mapper.LgcTalkBlacklistMapper;
import com.mylog.business.chat.dal.mapper.TeamMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class TeamService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(TeamService.class);

    @Resource
    private TeamMapper teamMapper;


    /**
     * 添加黑名单
     * @param teamName
     * @return
     */
    public TeamEntity getTeamByTeamName(String teamName){
        if (StringUtils.isBlank(teamName)){
            return null;
        }
        return teamMapper.getTeamByTeamName(teamName);
    }

}
