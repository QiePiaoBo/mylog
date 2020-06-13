package com.mylog.ds.blog.service.impl;

import com.mylog.ds.blog.service.IFileService;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.settings.Message;
import com.mylog.tools.lic.settings.Status;
import com.mylog.tools.lic.sysinfo.SysInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.mylog.tools.lic.settings.Size.UPLOAD_FILE_MAX_SIZE;

/**
 * 文件接口实现
 * @author Dylan
 */
@Service
public class FileServiceImpl implements IFileService {

    /**
     * 上传文件
     * @return
     */
    @Override
    public Result uploadFile(MultipartFile multipartFile){
        Boolean isWindows = new SysInfo().isWindows();
        long size= multipartFile.getSize();
        String filepath = "";
        //文件设置大小，我这里设置50M。
        if(size > UPLOAD_FILE_MAX_SIZE.getSize()){
            return new Result().put("status", 445).put("msg","File is too big.");
        }
        // 直接返回文件的名字
        String name=multipartFile.getOriginalFilename();
        // 我这里取得文件后缀
        String subffix = name.substring(name.lastIndexOf("."));
        // 文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + name;
        // 文件子目录
        String sonPath = new SimpleDateFormat("yyyyMM").format(new Date());
        // 根据服务器所处平台更改文件存储位置
        if (isWindows){
            // 获取文件存放位置
            filepath = "E:\\Files\\mylog\\" + subffix.substring(1) + "\\" + sonPath + "\\";
            File winFile = new File(filepath);
            // 目录不存在就创建
            if(!winFile.exists()){
                winFile.mkdirs();
            }
        }else {
                // 获取文件存放位置
                filepath = "/var/files/mylog/" + subffix.substring(1) + "/" + sonPath + "/";
                File LinuxFile = new File(filepath);
                // 目录不存在就创建
                if(!LinuxFile.exists()){
                    LinuxFile.mkdirs();
                }
            }
        try {
            multipartFile.transferTo(new File(filepath + fileName));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Result().put("status", Status.SUCCESS.getStatus()).put("msg", Message.SUCCESS.getMsg());
    }

}
