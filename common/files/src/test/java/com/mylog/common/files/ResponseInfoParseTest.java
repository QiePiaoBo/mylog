package com.mylog.common.files;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.tools.utils.utils.QiNiuTransfer;
import org.junit.Test;

/**
 * @Classname ResponseInfoParseTest
 * @Description ResponseInfoParseTest
 * @Date 9/19/2022 4:54 PM
 */
public class ResponseInfoParseTest {

    private static final MyLogger log = MyLoggerFactory.getLogger(ResponseInfoParseTest.class);

    @Test
    public void test1(){

        String responseInfo = "https://upload-z1.qiniup.com/  \n{ResponseInfo:com.qiniu.http.Response@1492aa65,status:200, reqId:PdcAAAB2as3HNhYX, xlog:X-Log, xvia:, adress:upload-z1.qiniup.com/110.242.48.29:443, duration:0.316000 s, error:null}  \n{\"key\":\"999.png\",\"hash\":\"Ft_3svHaIdP3Ch6SrEP2D6xoVFZ-\",\"bucket\":\"dylan-pic\",\"fsize\":10584}";
        log.info("res: {}", QiNiuTransfer.getInfoFromQiNiuResponse(responseInfo));
    }




}
