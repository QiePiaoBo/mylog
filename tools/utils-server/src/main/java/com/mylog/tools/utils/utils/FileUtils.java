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
            ins = multi.getInputStream();
            byte[] bytes = new byte[ins.available()];
            ins.read(bytes);

            File targetFile = new File("src/main/resources/temp/");

            FileOutputStream downloadFile = new FileOutputStream(targetFile);
            downloadFile.write(bytes);
            downloadFile.flush();
            downloadFile.close();
            return targetFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
