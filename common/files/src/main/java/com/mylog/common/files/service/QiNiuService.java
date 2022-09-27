package com.mylog.common.files.service;

import com.mylog.common.files.model.dto.FileUploadDTO;
import com.mylog.tools.model.model.result.DataResult;
import com.qiniu.storage.model.FileInfo;

import java.util.List;

/**
 * @Classname QiNiuService
 * @Description QiNiuService
 * @Date 9/15/2022 5:36 PM
 */
public interface QiNiuService {

    DataResult upload2QiNiu(FileUploadDTO dto);

    List<FileInfo> queryFileList();

    void getQiNiuLog();
}
