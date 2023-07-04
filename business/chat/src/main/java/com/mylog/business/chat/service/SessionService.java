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
        LgcTalkSessionEntity entity = lgcTalkSessionMapper.getSessionByUserName(userName, talkWith);
        if (Objects.nonNull(entity)){
            logger.info("<getOrCreateSession> Got entity: {}", entity);
            return entity.getSessionId();
        }else {
            List<UserNameIdModel> userNameIds = lgcTalkSessionMapper.getUserNameId(Arrays.asList(userName, talkWith));
            Map<String, Integer> userNameIdMap = Safes.of(userNameIds).stream().filter(m -> m.getId() > 0).collect(Collectors.toMap(UserNameIdModel::getUserName, UserNameIdModel::getId, (v1, v2) -> v2));
            if (userNameIdMap.size() < 2){
                logger.error("<getOrCreateSession> Error getting username id map : {}", userNameIds);
                return null;
            }
            SessionInsertModel sessionInsertModel = new SessionInsertModel();
            sessionInsertModel.setSenderId(userNameIdMap.get(userName));
            sessionInsertModel.setRecipientId(userNameIdMap.get(talkWith));
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
                List<LgcTalkSessionEntity> entities = lgcTalkSessionMapper.querySessions(sessionQueryModel);
                return entities.get(0).getSessionId();
            }
        }
    }
}
