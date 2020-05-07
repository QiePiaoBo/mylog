package com.dylan.mylog.config.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
@Data
public class TestPropertyService {

    @Value("${userName}")
    String userName;
    @Value("${userAge}")
    String userAge;



}
