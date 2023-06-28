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
import com.mylog.business.chat.model.converter.LgcTalkSessionConverter;
import com.mylog.business.chat.model.converter.MsgRecordConverter;
import com.mylog.business.chat.model.vo.LgcTalkSessionVO;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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


}
