package com.mylog.common.files.model.transfer;

import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.model.entity.FileUpload;
import com.mylog.tools.model.constant.FileConstant;
import com.mylog.tools.model.model.dto.QiNiuFileInfo;
import com.mylog.tools.utils.utils.QiNiuTransfer;
import com.mylog.tools.utils.utils.Safes;

import java.util.Objects;

/**
 * @Classname FileUploadTransfer
 * @Description FileUploadTransfer
 * @Date 9/20/2022 1:50 PM
 */
public class FileUploadTransfer {

    /**
     * 入参转化为实体 字符串默认值为空
     * @param model
     * @return
     */
    public static FileUpload model2FileUpload(FileUploadModel model){
        FileUpload res = new FileUpload();
        res.setId(model.getId());
        res.setType(Safes.of(model.getType(), 0));
        res.setUploadInfo(Safes.of(model.getUploadInfo()));
        res.setFileName(Safes.of(model.getFileName()));
        res.setFileUri(Safes.of(model.getFileUri()));
        res.setFileSize(model.getFileSize());
        res.setHash(Safes.of(model.getHash()));
        res.setBucket(Safes.of(model.getBucket()));

        res.setDelFlag(Safes.of(model.getDelFlag(), Boolean.FALSE));
        res.setCreatedAt(model.getCreatedAt());
        res.setUpdatedAt(model.getUpdatedAt());
        return res;
    }

    public static FileUploadModel getModelFromQiNiuRespInfo(String responseInfo){
        QiNiuFileInfo qiNiuFileInfo = QiNiuTransfer.getInfoFromQiNiuResponse(responseInfo);
        if (Objects.isNull(qiNiuFileInfo)){
            return null;
        }
        FileUploadModel result = new FileUploadModel();
        result.setFileName(qiNiuFileInfo.getKey());
        result.setFileUri(FileConstant.QINIU_FILE_PREFIX + qiNiuFileInfo.getKey());
        result.setType(0);
        result.setFileSize(qiNiuFileInfo.getFsize());
        result.setBucket(qiNiuFileInfo.getBucket());
        result.setHash(qiNiuFileInfo.getHash());
        result.setUploadInfo(responseInfo);
        return result;
    }


}
