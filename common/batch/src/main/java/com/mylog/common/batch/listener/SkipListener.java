package com.mylog.common.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.listener.CompositeSkipListener;
import org.springframework.stereotype.Component;

/**
 * 通用skip监听器
 * @author Dylan
 */
@Slf4j
@Component
public class SkipListener extends CompositeSkipListener {
}
