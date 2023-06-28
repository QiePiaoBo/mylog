package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.ws.WebSocketUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

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
        String messageAimingUser2 = WebSocketUtil.getMessageAimingUser("Server: Member - duke left.<@@all>");

        logger.info("res: {}", messageAimingUser);
        logger.info("res1: {}", messageAimingUser1);
        logger.info("res2: {}", messageAimingUser2);
    }

    @Test
    public void commonTest() {
        int i = Integer.parseInt("123");

        Integer integer = Integer.getInteger("345");

        logger.info("res: {}, {}", i, integer);
    }

    @Test
    public void testQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(5);

        for (int i = 0; i < 3; i++) {
            Integer take = queue.take();
            logger.info("take : {}", take);
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(queue);
        logger.info("res: {}", list);

    }


}
