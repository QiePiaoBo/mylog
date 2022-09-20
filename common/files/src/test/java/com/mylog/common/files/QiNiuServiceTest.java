package com.mylog.common.files;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.service.FileUploadService;
import com.mylog.common.files.service.QiNiuService;
import com.qiniu.storage.model.FileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname FileUploadServiceTest
 * @Description FileUploadServiceTest
 * @Date 9/20/2022 1:43 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QiNiuServiceTest {

    private final MyLogger log = MyLoggerFactory.getLogger(QiNiuServiceTest.class);

    @Resource
    private QiNiuService qiNiuService;

    @Test
    public void test01(){

        List<FileInfo> fileInfos = qiNiuService.queryFileList();



        log.info("res: {}", fileInfos);
    }



}