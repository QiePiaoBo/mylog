package com.mylog.common.files.service.impl;

import com.mylog.common.files.mapper.FileUploadMapper;
import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.model.entity.FileUploadEntity;
import com.mylog.common.files.model.transfer.FileUploadTransfer;
import com.mylog.common.files.service.FileUploadService;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname FileUploadServiceImpl
 * @Description FileUploadServiceImpl
 * @Date 9/20/2022 1:48 PM
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileUploadMapper fileUploadMapper;

    @Override
    public DataResult insert(FileUploadModel model) {
        int insert = fileUploadMapper.insert(FileUploadTransfer.model2FileUpload(model));
        if (insert > 0){
            return DataResult.success(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(model.getFileUri()).build();
        }
        return DataResult.fail(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).data(model.getFileUri()).build();
    }

    @Override
    public DataResult batchInsert(List<FileUploadModel> entities){
        int insert = fileUploadMapper.insertUploads(entities);
        if (insert > 0){
            return DataResult.success(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).build();
        }
        return DataResult.fail(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
    }
}
