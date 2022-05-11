package com.mylog.common.licence.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Classname UserAccessServiceTest
 * @Description UserAccessServiceTest
 * @Date 5/11/2022 2:50 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccessServiceTest {

    @Resource
    private IUserAccessService userAccessService;



}
