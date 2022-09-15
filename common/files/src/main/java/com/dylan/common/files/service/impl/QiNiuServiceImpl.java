package com.dylan.common.files.service.impl;

import com.dylan.common.files.dto.FileUploadDTO;
import com.dylan.common.files.service.QiNiuService;
import com.mylog.tools.model.constant.FileConstant;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.sdks.filesdk.QiNiuSdk;
import com.mylog.tools.utils.utils.FileUtils;
import com.mylog.tools.utils.utils.StringSafeUtil;
import com.qiniu.http.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Classname QiNiuServiceImpl
 * @Description QiNiuServiceImpl
 * @Date 9/15/2022 5:37 PM
 */
@Service
public class QiNiuServiceImpl implements QiNiuService {

    private static final Logger log = LoggerFactory.getLogger(QiNiuServiceImpl.class);

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
            fileName = multipartFile.getOriginalFilename();
            file = FileUtils.multi2File(multipartFile);
            if (Objects.nonNull(file)){
                response = QiNiuSdk.uploadToQiniu(file, accessKey, secretKey, bucketName);
            }
        }catch (IOException e){
            log.error("Error parsing file, reason: {}", e.getMessage(), e);
        }
        if (Objects.nonNull(response) && response.isOK()){
            log.info("response: {}", response);
            // todo 解析Response中的信息并将文件相关信息存入数据库
            String fileURI = FileConstant.QINIU_FILE_PREFIX + fileName;
            String responseInfo = response.getInfo();
            log.info("fileURI: {}, responseInfo: {}", fileURI, responseInfo);
            if (file.exists()){
                file.delete();
            }
            return DataResult.getBuilder().data(response).build();
        }
        return DataResult.fail().build();
    }
}
