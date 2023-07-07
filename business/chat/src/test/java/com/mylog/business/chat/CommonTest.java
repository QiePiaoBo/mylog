package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.ws.WebSocketUtil;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void testConvertLocalDateTime() {
        // 转换为 LocalDateTime 对象
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        logger.info("Current is {}", localDateTime);
    }


    @Test
    public void makeMyOwnEntity(){
        Pattern linePattern = Pattern.compile("_(\\w)");

        String originalProperties = "id, user_type, user_name, user_phone, user_password, user_group, created_at, updated_at, del_flag";

        String[] split = originalProperties.split(",");
        for (String str : split){
            str = str.trim();
            Matcher matcher = linePattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            logger.info("private Object {};", sb);
        }
    }

    @Test
    public void testSubString() {

        String s = "asdasd&->";

        logger.info("res: {}", s.substring(0, s.indexOf("&")));
    }

}
