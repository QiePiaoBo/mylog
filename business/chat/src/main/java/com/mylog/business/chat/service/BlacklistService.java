package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.mapper.LgcTalkBlacklistMapper;
import com.mylog.business.chat.dal.mapper.LgcTalkSessionMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import com.mylog.business.chat.model.SessionInsertModel;
import com.mylog.business.chat.model.SessionQueryModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.business.chat.model.converter.LgcTalkSessionConverter;
import com.mylog.business.chat.model.vo.LgcTalkSessionVO;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class BlacklistService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(BlacklistService.class);

    @Resource
    private LgcTalkBlacklistMapper blacklistMapper;


    /**
     * 添加黑名单
     * @param model
     * @return
     */
    public boolean addBlackList(BlacklistInsertModel model){

        // todo 检查参数 参数预处理
        return blacklistMapper.addBlacklist(model) > 0;
    }

}
