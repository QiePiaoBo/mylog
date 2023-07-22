package com.mylog.business.chat.service;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.mapper.ConfettiMapper;
import com.mylog.business.chat.dal.mapper.UserMapper;
import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname MsgRecordService
 * @Description MsgRecordService
 * @Date 6/28/2023 3:27 PM
 */
@Service
public class UserService {

    private static final MyLogger logger = MyLoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;


    /**
     * 获取用户Id
     * @param userName
     * @return
     */
    public HttpResult getUserId(String userName){
        List<UserNameIdModel> userNameIds = userMapper.getUserNameId(Arrays.asList(userName));
        if (userNameIds.size() != 1) {
            return DataResult.getBuilder().data(0).build();
        }
        return DataResult.getBuilder().data(userNameIds.get(0).getId()).build();
    }

}
