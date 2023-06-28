package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.mapper.MsgRecordMapper;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.MsgQueryModel;
import com.mylog.business.chat.model.converter.MsgRecordConverter;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class MsgRecordService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(MsgRecordService.class);

    @Resource
    private MsgRecordMapper msgRecordMapper;

    /**
     * 获取消息列表
     * @return
     */
    public List<MsgRecordVO> getMsgRecordForClient(MsgQueryModel queryModel) {
        List<MsgRecordEntity> msgRecordEntities = msgRecordMapper.queryMsgRecords(queryModel);
        return Safes.of(msgRecordEntities).stream().map(MsgRecordConverter::getVoForEntity).collect(Collectors.toList());
    }


    /**
     * 消息批量插入
     * @param insertModels
     * @return
     */
    public boolean msgRecordBatchInsert(List<MsgInsertModel> insertModels) {
        // 消息处理
        checkAndDealingMsg(insertModels);
        Integer integer = msgRecordMapper.batchInsertMsgRecord(insertModels);
        return integer > 0;
    }

    /**
     * 处理待插入消息
     * @param insertModels
     */
    private void checkAndDealingMsg(List<MsgInsertModel> insertModels) {
        logger.info("msg size: {}", insertModels.size());
    }


}
