package com.mylog.common.files.service;

import com.mylog.common.files.model.dto.FileUploadDTO;
import com.mylog.tools.model.model.result.DataResult;

/**
 * @Classname QiNiuService
 * @Description QiNiuService
 * @Date 9/15/2022 5:36 PM
 */
public interface QiNiuService {

    public DataResult upload2QiNiu(FileUploadDTO dto);


}
