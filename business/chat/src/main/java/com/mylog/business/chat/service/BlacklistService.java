package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.mapper.BlacklistMapper;
import com.mylog.business.chat.model.BlacklistInsertModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class BlacklistService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(BlacklistService.class);

    @Resource
    private BlacklistMapper blacklistMapper;


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
