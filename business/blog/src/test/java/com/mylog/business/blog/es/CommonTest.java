package com.mylog.business.blog.es;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Dylan
 * @Description CommonTest
 * @Date : 2022/12/17 - 23:29
 */
@SpringBootTest
public class CommonTest {



    @Test
    public void testLogStatus() {
        String s = "{\"name\":\"dylan\"}";

        String s1 = s.replaceAll("\"", "\\\\\"");

        System.out.println(s1);

    }

}
