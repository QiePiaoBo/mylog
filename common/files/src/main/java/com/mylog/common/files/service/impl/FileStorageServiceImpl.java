package com.mylog.common.files.service.impl;

import com.mylog.common.files.mapper.FileStorageMapper;
import com.mylog.common.files.model.FileStorageModel;
import com.mylog.common.files.model.transfer.FileStorageTransfer;
import com.mylog.common.files.service.FileStorageService;
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
public class FileStorageServiceImpl implements FileStorageService {

    @Resource
    private FileStorageMapper fileStorageMapper;

    @Override
    public DataResult insert(FileStorageModel model) {
        int insert = fileStorageMapper.insert(FileStorageTransfer.model2FileUpload(model));
        if (insert > 0){
            return DataResult.success(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(model.getFileUri()).build();
        }
        return DataResult.fail(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).data(model.getFileUri()).build();
    }

    @Override
    public DataResult batchInsert(List<FileStorageModel> entities){
        fileStorageMapper.insertStorages(entities);
        return DataResult.success(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).build();
    }
}
