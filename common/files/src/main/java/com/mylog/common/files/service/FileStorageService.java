package com.mylog.common.files.service;

import com.mylog.common.files.model.FileStorageModel;
import com.mylog.tools.model.model.result.DataResult;

import java.util.List;

/**
 * @Classname FileUploadService
 * @Description TODO
 * @Date 9/19/2022 6:47 PM
 */
public interface FileStorageService {

    /**
     * 插入
     * @param model
     * @return
     */
    DataResult insert(FileStorageModel model);

    /**
     * 批量插入
     * @param entities
     * @return
     */
    DataResult batchInsert(List<FileStorageModel> entities);

}
