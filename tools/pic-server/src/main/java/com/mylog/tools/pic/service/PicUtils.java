package com.mylog.tools.pic.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Dylan
 * @Date : Created in 15:01 2020/8/21
 * @Description :
 * @Function :
 */
public class PicUtils {
    private static final Logger logger = LoggerFactory.getLogger(PicUtils.class);
    private static final long expireSeconds = 3600;

    public static Response uploadToQiniu(File file, String accessKey, String secretKey, String bucketName) throws QiniuException {
        // 默认本地文件名作为上传的文件名
        String fileName = file.getName();
        // 自定义返回格式
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        // 凭证管理器 uploadToken的第二个参数null
        Auth auth = Auth.create(accessKey, secretKey);
        String accessToken = auth.uploadToken(bucketName, null, expireSeconds, putPolicy);
        // 上传配置，目前只设置了存储地址
        Configuration configuration = new Configuration(Region.autoRegion());
        // 上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        // 上传文件至七牛 第一个参数是文件，第二个是存储时的名字，第三个是存储空间的token
        Response uploadResponse = uploadManager.put(file, fileName, accessToken);
        return uploadResponse;
    }
}
