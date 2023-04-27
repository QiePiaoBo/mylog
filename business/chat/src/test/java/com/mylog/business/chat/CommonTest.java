package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.ws.WebSocketUtil;
import org.junit.Test;

/**
 * @author Dylan
 * @Description CommonTest
 * @Date : 2023/4/27 - 23:56
 */
public class CommonTest {

    private final MyLogger logger = MyLoggerFactory.getLogger(CommonTest.class);

    @Test
    public void testGetAimingUser() {

        String messageAimingUser = WebSocketUtil.getMessageAimingUser("asdasdasd<@dylan>");
        String messageAimingUser1 = WebSocketUtil.getMessageAimingUser("asdasdasd<@dylan");

        logger.info("res: {}", messageAimingUser);
        logger.info("res1: {}", messageAimingUser1);
    }

}
