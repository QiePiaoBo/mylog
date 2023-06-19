package com.mylog.business.chat;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.dal.HBaseService;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.NavigableMap;

/**
 * @Classname HBaseTest
 * @Description HBaseTest
 * @Date 5/24/2023 10:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HBaseTest {

    private static final MyLogger logger = MyLoggerFactory.getLogger(HBaseTest.class);

    @Resource
    private HBaseService hBaseService;

    @Test
    public void testCreateTable() throws IOException {
        hBaseService.createTable("test2", "testFamily");
    }

    @Test
    public void testInsert() throws IOException {
        hBaseService.insert("test", "t02", "test1", "1", "TEST02");
    }

    @Test
    public void testGet() throws IOException {
        String res = hBaseService.queryByRowKey("test", "t01", "test1", "1");
        logger.info("res: {}", res);
    }

    @Test
    public void testDeleteTable() throws IOException {
        hBaseService.deleteTable("test2");
    }

    @Test
    public void testQueryAll() throws IOException {
        ResultScanner scanner = hBaseService.queryAll("test");
        for (Result result : scanner) {
            NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> navigableMap = result.getMap();
            for (byte[] family : navigableMap.keySet()) {
                logger.info("列族:" + new String(family));
                for (byte[] column : navigableMap.get(family).keySet()) {
                    logger.info("列:" + new String(column));
                    for (Long t : navigableMap.get(family).get(column).keySet()) {
                        logger.info("键: {}, 值: {}", t, new String(navigableMap.get(family).get(column).get(t)));
                    }
                }
            }
        }
    }

    @Test
    public void testDeleteRow() throws IOException {
        hBaseService.deleteRecord("test", "test1", "1", "t01");
    }


}
