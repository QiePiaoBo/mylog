package com.mylog.common.files;

import com.mylog.common.files.model.FileStorageModel;
import com.mylog.common.files.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Classname FileUploadServiceTest
 * @Description FileUploadServiceTest
 * @Date 9/20/2022 1:43 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileStorageServiceTest {

    @Resource
    private FileStorageService fileStorageService;

    @Test
    public void test01(){

        FileStorageModel fileStorageModel = new FileStorageModel();
        fileStorageModel.setBucket("test");
        fileStorageModel.setFileName("test");
        fileStorageModel.setFileUri("test");
        fileStorageModel.setHash("test");
        fileStorageModel.setType(1);
        fileStorageModel.setUploadInfo("test");

        fileStorageService.insert(fileStorageModel);
    }



}
