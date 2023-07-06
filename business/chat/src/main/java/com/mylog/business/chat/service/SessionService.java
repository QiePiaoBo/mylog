package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.mapper.LgcTalkSessionMapper;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.dal.mapper.UserMapper;
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

    @Resource
    private UserMapper userMapper;


    /**
     * 按条件查询session
     *
     * @param queryModel
     * @return
     */
    public List<LgcTalkSessionVO> querySessions(SessionQueryModel queryModel) {
        if (queryModel.isValid()) {
            return new ArrayList<>();
        }
        queryModel.confirmId();
        List<LgcTalkSessionEntity> entities = lgcTalkSessionMapper.querySessions(queryModel);
        return Safes.of(entities).stream().map(LgcTalkSessionConverter::getVoForEntity).collect(Collectors.toList());
    }


    /**
     * 创建session
     *
     * @return
     */
    public boolean createSession(SessionInsertModel insertModel) {
        if (!insertModel.isOk()) {
            return false;
        }
        insertModel.confirmId();
        Integer integer = lgcTalkSessionMapper.insertSession(insertModel);
        return integer > 0;
    }


    /**
     * 根据用户名查询或新建session
     *
     * @param userName
     * @param talkWith
     * @return
     */
    public Integer getOrCreateSession(String userName, String talkWith) {
        // 两个名字 前后顺序都进行查询 需要优化 在插入session时就将Id根据大小顺序进行排列 保证两个人只会产生一条会话记录
        // 根据userName查询两个用户的
        List<UserNameIdModel> userNameIds = userMapper.getUserNameId(Arrays.asList(userName, talkWith));
        Map<String, Integer> userNameIdMap = Safes.of(userNameIds).stream().filter(m -> m.getId() > 0).collect(Collectors.toMap(UserNameIdModel::getUserName, UserNameIdModel::getId, (v1, v2) -> v2));
        if (userNameIdMap.size() < 2) {
            logger.error("<getOrCreateSession> Error getting username id map : {}", userNameIds);
            return null;
        }
        // 构造查询对象
        SessionQueryModel sessionQueryModel = SessionQueryModel
                .builder()
                .senderId(userNameIdMap.getOrDefault(talkWith, 0))
                .recipientId(userNameIdMap.getOrDefault(userName, 0))
                .build();
        // 构造插入对象
        SessionInsertModel sessionInsertModel = SessionInsertModel
                .builder()
                .senderId(userNameIdMap.get(userName))
                .recipientId(userNameIdMap.get(talkWith))
                .build();
        // 首次查询
        List<LgcTalkSessionVO> vos = this.querySessions(sessionQueryModel);
        if (vos.size() > 0) {
            LgcTalkSessionVO sessionVO = vos.get(0);
            logger.info("<getOrCreateSession> Got entityFromTo: {}", sessionVO);
            if (Objects.nonNull(sessionVO)) {
                return sessionVO.getSessionId();
            }
        } else {
            // 创建session
            if (this.createSession(sessionInsertModel)) {
                logger.info("<getOrCreateSession> Session inserted, {}", sessionInsertModel);
            } else {
                logger.error("<getOrCreateSession> Error param of sessionInsertModel: {}", sessionInsertModel);
                return null;
            }
            // 如果回补失败，就查询并取第一个值的id进行返回
            if (Objects.nonNull(sessionInsertModel.getSessionId())) {
                return sessionInsertModel.getSessionId();
            } else {
                // 插入成功后再次查询
                List<LgcTalkSessionVO> entityList = this.querySessions(sessionQueryModel);
                return entityList.get(0).getSessionId();
            }
        }
        logger.error("<getOrCreateSession> Error getting sessionId for {} and {}", userName, talkWith);
        return null;
    }

    /**
     * 根据用户名获取用户名Id映射
     *
     * @param userNames
     * @return
     */
    public Map<String, Integer> getUserNameIdMap(List<String> userNames) {
        List<UserNameIdModel> userNameIds = userMapper.getUserNameId(userNames);
        Map<String, Integer> userNameIdMap = Safes.of(userNameIds).stream().filter(m -> m.getId() > 0).collect(Collectors.toMap(UserNameIdModel::getUserName, UserNameIdModel::getId, (v1, v2) -> v2));
        if (userNameIdMap.size() != userNames.size()) {
            logger.error("<getOrCreateSession> Error getting username id map : {}", userNameIds);
            return null;
        }
        return userNameIdMap;
    }
}
