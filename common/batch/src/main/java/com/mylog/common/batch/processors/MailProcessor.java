package com.mylog.common.batch.processors;

import com.mylog.common.batch.model.UserEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class MailProcessor implements ItemProcessor<UserEntity, UserEntity> {

    @Autowired


    @Override
    public UserEntity process(UserEntity userEntity) throws Exception {

        String mail = userEntity.getMail();

        return userEntity;

    }
}
