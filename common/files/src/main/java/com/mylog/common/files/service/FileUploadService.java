package com.mylog.common.files.service;

import com.mylog.common.files.model.FileUploadModel;
import com.mylog.tools.model.model.result.DataResult;

/**
 * @Classname FileUploadService
 * @Description TODO
 * @Date 9/19/2022 6:47 PM
 */
public interface FileUploadService {

    DataResult insert(FileUploadModel model);


}
