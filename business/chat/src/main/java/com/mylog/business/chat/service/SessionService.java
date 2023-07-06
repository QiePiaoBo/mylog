package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.mapper.LgcTalkSessionMapper;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.MsgQueryModel;
import com.mylog.business.chat.model.SessionInsertModel;
import com.mylog.business.chat.model.SessionQueryModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.business.chat.model.converter.LgcTalkSessionConverter;
import com.mylog.business.chat.model.converter.MsgRecordConverter;
import com.mylog.business.chat.model.vo.LgcTalkSessionVO;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class SessionService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(SessionService.class);

    @Resource
    private LgcTalkSessionMapper lgcTalkSessionMapper;


    /**
     * 按条件查询session
     * @param queryModel
     * @return
     */
    public List<LgcTalkSessionVO> querySessions(SessionQueryModel queryModel){
        if (queryModel.isValid()){
            return new ArrayList<>();
        }
        List<LgcTalkSessionEntity> entities = lgcTalkSessionMapper.querySessions(queryModel);
        return Safes.of(entities).stream().map(LgcTalkSessionConverter::getVoForEntity).collect(Collectors.toList());
    }


    /**
     * 创建session
     * @return
     */
    public boolean createSession(SessionInsertModel insertModel){
        if (!insertModel.isOk()){
            return false;
        }
        insertModel.confirmId();
        Integer integer = lgcTalkSessionMapper.insertSession(insertModel);
        return integer > 0;
    }


    /**
     * 根据用户名查询或新建session
     * @param userName
     * @param talkWith
     * @return
     */
    public Integer getOrCreateSession(String userName, String talkWith) {
        // todo 两个名字 前后顺序都进行查询 需要优化 在插入session时就将Id根据大小顺序进行排列 保证两个人只会产生一条会话记录
        // 根据userName查询两个用户的
        List<UserNameIdModel> userNameIds = lgcTalkSessionMapper.getUserNameId(Arrays.asList(userName, talkWith));
        Map<String, Integer> userNameIdMap = Safes.of(userNameIds).stream().filter(m -> m.getId() > 0).collect(Collectors.toMap(UserNameIdModel::getUserName, UserNameIdModel::getId, (v1, v2) -> v2));
        if (userNameIdMap.size() < 2){
            logger.error("<getOrCreateSession> Error getting username id map : {}", userNameIds);
            return null;
        }
        SessionQueryModel queryModel = new SessionQueryModel();
        queryModel.setSenderId(userNameIdMap.getOrDefault(talkWith, 0));
        queryModel.setRecipientId(userNameIdMap.getOrDefault(userName, 0));
        queryModel.confirmId();
        List<LgcTalkSessionEntity> entities = lgcTalkSessionMapper.querySessions(queryModel);
        if (entities.size() > 0){
            LgcTalkSessionEntity entityToFrom = entities.get(0);
            logger.info("<getOrCreateSession> Got entityFromTo: {}", entityToFrom);
            if (Objects.nonNull(entityToFrom)){
                return entityToFrom.getSessionId();
            }
        }else {
            SessionInsertModel sessionInsertModel = new SessionInsertModel();
            sessionInsertModel.setSenderId(userNameIdMap.get(userName));
            sessionInsertModel.setRecipientId(userNameIdMap.get(talkWith));
            if (!sessionInsertModel.isOk()){
                logger.error("<getOrCreateSession> Error param of sessionInsertModel: {}", sessionInsertModel);
                return null;
            }
            sessionInsertModel.confirmId();
            // Id会回补到SessionInsertModel
            Integer integer = lgcTalkSessionMapper.insertSession(sessionInsertModel);
            if (integer > 0){
                logger.info("<getOrCreateSession> Session inserted, {}", sessionInsertModel);
            }
            // 如果回补失败，就查询并取第一个值的id进行返回
            if (Objects.nonNull(sessionInsertModel.getSessionId())){
                return sessionInsertModel.getSessionId();
            }else {
                SessionQueryModel sessionQueryModel = new SessionQueryModel();
                sessionQueryModel.setSenderId(userNameIdMap.get(userName));
                sessionQueryModel.setRecipientId(userNameIdMap.get(talkWith));
                sessionQueryModel.confirmId();
                List<LgcTalkSessionEntity> entityList = lgcTalkSessionMapper.querySessions(sessionQueryModel);
                return entityList.get(0).getSessionId();
            }
        }
        logger.error("<getOrCreateSession> Error getting sessionId for {} and {}", userName, talkWith);
        return null;
    }

    /**
     * 根据用户名获取用户名Id映射
     * @param userNames
     * @return
     */
    public Map<String, Integer> getUserNameIdMap(List<String> userNames){
        List<UserNameIdModel> userNameIds = lgcTalkSessionMapper.getUserNameId(userNames);
        Map<String, Integer> userNameIdMap = Safes.of(userNameIds).stream().filter(m -> m.getId() > 0).collect(Collectors.toMap(UserNameIdModel::getUserName, UserNameIdModel::getId, (v1, v2) -> v2));
        if (userNameIdMap.size() != userNames.size()){
            logger.error("<getOrCreateSession> Error getting username id map : {}", userNameIds);
            return null;
        }
        return userNameIdMap;
    }
}
