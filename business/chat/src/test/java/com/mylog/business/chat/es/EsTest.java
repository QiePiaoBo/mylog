package com.mylog.business.chat.es;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dylan
 * @Description EsTest
 * @Date : 2022/12/6 - 23:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    private static final MyLogger log = MyLoggerFactory.getLogger(EsTest.class);

    @Autowired
    @Qualifier("mylogEsNode1")
    private RestHighLevelClient mylogEsNode1;



    @Test
    public void testCreateIndex() throws Exception {

        CreateIndexRequest request = new CreateIndexRequest("mylog_test_index");
        CreateIndexResponse createIndexResponse = mylogEsNode1.indices().create(request, RequestOptions.DEFAULT);
        log.info("res: {}", createIndexResponse);
    }




}
