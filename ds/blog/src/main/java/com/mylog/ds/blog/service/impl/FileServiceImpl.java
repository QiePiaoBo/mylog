package com.mylog.ds.blog.service.impl;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.entity.Message;
import com.mylog.tools.lic.entity.Status;
import com.mylog.tools.lic.sysinfo.SysInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;
    /**
     * 上传文件
     * @return
     */
    @Override
    public Result uploadFile(MultipartFile multipartFile, ArticleDto articleDto){
        Boolean isWindows = new SysInfo().isWindows();
        long size= multipartFile.getSize();
        String filepath = "";
        //文件设置大小，我这里设置50M。
        if(size > UPLOAD_FILE_MAX_SIZE.getSize()){
            return new Result().put("status", Status.OUTOF_SIZE_ERROR.getStatus()).put("msg", Message.OUTOF_SIZE_ERROR.getMsg());
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
        } else {
                // 获取文件存放位置
                filepath = "/var/files/mylog/" + subffix.substring(1) + "/" + sonPath + "/";
                File linuxFile = new File(filepath);
                // 目录不存在就创建
                if(!linuxFile.exists()){
                    linuxFile.mkdirs();
                }
            }
        // 文件全路径拼接上文件名
        filepath += fileName;
        // 是否入库成功
        if(this.insertToDatabase(filepath, name, articleDto)){
            try {
                multipartFile.transferTo(new File(filepath));
            }catch (IOException e) {
                e.printStackTrace();
            }
            return new Result().put("status", Status.SUCCESS.getStatus()).put("msg", Message.SUCCESS.getMsg()).put("data", sonPath + "/" + fileName);
        }
        else {
            return new Result().put("status", Status.INSERT_ERROR.getStatus()).put("msg", Message.INSERT_ERROR.getMsg());
        }
    }

    private Boolean insertToDatabase(String filePath, String fileName, ArticleDto articleDto){
        // 插入数据库
        Person currentUser = userService.getUser();
        Article article = new Article();
        if (currentUser != null){
            article.setIsDel("0");
            article.setFileName(fileName);
            article.setCreateTime(new Date());
            article.setUserId(currentUser.getId());
            article.setFilePath(filePath);
            article.setDescription(articleDto.getDescription());
            article.setSubTitle(articleDto.getSubTitle());
            article.setFileType(articleDto.getFileType());
            article.setIsLock(articleDto.getIsLock()!=null ? articleDto.getIsLock():"0");
        }
        else{
            return false;
        }
        if (articleService.insert(article)!=null){
            return true;
        }
        return false;

    }
}
