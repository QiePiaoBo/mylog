package com.mylog.common.files;

import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.service.FileUploadService;
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
public class FileUploadEntityServiceTest {

    @Resource
    private FileUploadService fileUploadService;

    @Test
    public void test01(){

        FileUploadModel fileUploadModel = new FileUploadModel();
        fileUploadModel.setBucket("test");
        fileUploadModel.setFileName("test");
        fileUploadModel.setFileUri("test");
        fileUploadModel.setHash("test");
        fileUploadModel.setType(1);
        fileUploadModel.setUploadInfo("test");

        fileUploadService.insert(fileUploadModel);
    }



}
