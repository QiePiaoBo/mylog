package com.mylog.tools.utils.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Dylan
 * @Date : Created in 15:50 2020/8/21
 * @Description : 文件相关操作方法
 * @Function :
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static File multi2File(MultipartFile multi){
        InputStream ins = null;
        File f = null;
        if (multi.getSize() <= 0){
            logger.error("MultipartFile is null.");
        }
        try {
            String filePath = "F:\\Files\\mylog\\tmp\\" + multi.getOriginalFilename();
            ins = multi.getInputStream();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(filePath);
            while ((index = ins.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            ins.close();
            downloadFile.close();
            return new File(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
