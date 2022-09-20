package com.mylog.common.files.service.impl;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.model.dto.FileUploadDTO;
import com.mylog.common.files.model.transfer.FileUploadTransfer;
import com.mylog.common.files.service.FileUploadService;
import com.mylog.common.files.service.QiNiuService;
import com.mylog.tools.model.constant.FileConstant;
import com.mylog.tools.model.model.exception.MyException;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.sdks.filesdk.QiNiuSdk;
import com.mylog.tools.utils.utils.FileUtils;
import com.mylog.tools.utils.utils.StringSafeUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.model.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Classname QiNiuServiceImpl
 * @Description QiNiuServiceImpl
 * @Date 9/15/2022 5:37 PM
 */
@Service
public class QiNiuServiceImpl implements QiNiuService {

    private static final MyLogger log = MyLoggerFactory.getLogger(QiNiuServiceImpl.class);

    @Resource
    private FileUploadService fileUploadService;

    @Value("${qiniu.accesskey:}")
    String accessKey;
    @Value("${qiniu.secretkey:}")
    String secretKey;
    @Value("${qiniu.bucketname:}")
    String bucketName;

    /**
     * 上传到七牛云
     *
     * @param dto
     * @return
     */
    @Override
    public DataResult upload2QiNiu(FileUploadDTO dto){
        Response response = null;
        if (StringUtils.isBlank(accessKey) || StringUtils.isBlank(secretKey) || StringUtils.isBlank(bucketName)){
            log.error("Cannot get qi niu config: accessKey={}, secretKey={}, bucketName={}",
                    StringSafeUtil.hideMiddleString(accessKey),
                    StringSafeUtil.hideMiddleString(secretKey),
                    StringSafeUtil.hideMiddleString(bucketName));
            return DataResult.fail(Status.PARAM_NEED.getStatus(), Message.PARAM_NEED.getMsg()).build();
        }
        String fileName = null;
        File file = null;
        try {
            MultipartFile multipartFile = dto.getFile();
            if (Objects.isNull(multipartFile)){
                throw new MyException(Message.PARAM_NEED.getMsg());
            }
            fileName = multipartFile.getOriginalFilename();
            file = FileUtils.multi2File(multipartFile);
            if (Objects.nonNull(file)){
                response = QiNiuSdk.uploadToQiniu(file, accessKey, secretKey, bucketName);
                // 上传之后将临时文件删掉
                if (file.exists()){
                    boolean deleted = file.delete();
                    if (deleted){
                        log.info("Temp File : {} deleted.", file.getName());
                    }
                }
            }
        }catch (IOException e){
            log.error("Error parsing file, reason: {}", e.getMessage(), e);
        }
        if (Objects.nonNull(response) && response.isOK()){
            log.info("response: {}", response);
            String fileURI = FileConstant.QINIU_FILE_PREFIX + fileName;
            String responseInfo = response.getInfo();
            log.info("fileURI: {}, responseInfo: {}", fileURI, responseInfo);
            FileUploadModel model = FileUploadTransfer.getModelFromQiNiuRespInfo(responseInfo);
            if (Objects.nonNull(model)){
                return fileUploadService.insert(model);
            }
            return DataResult.success().build();
        }
        return DataResult.fail().build();
    }

    @Override
    public List<FileInfo> queryFileList() {
        return QiNiuSdk.queryFileList(bucketName, "", accessKey, secretKey, "");
    }
}
