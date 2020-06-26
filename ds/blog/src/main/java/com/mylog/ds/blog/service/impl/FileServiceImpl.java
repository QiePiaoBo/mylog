package com.mylog.ds.blog.service.impl;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.entity.vo.UserVO;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.entity.Message;
import com.mylog.tools.lic.entity.Status;
import com.mylog.tools.lic.sysinfo.SysInfo;
import org.springframework.beans.BeanUtils;
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
    public Result uploadFile(ArticleDto articleDto){
        // 判断是否是windows平台
        Boolean isWindows = new SysInfo().isWindows();
        // 获取传来的文件
        MultipartFile multipartFile = articleDto.getFile();
        // 设置最大大小
        long maxSize = 52428800;
        long size= multipartFile.getSize();
        String filepath = "";
        // 文件大小是否超过最大大小。
        if(size > maxSize){
            return new Result().put("status", Status.OUTOF_SIZE_ERROR.getStatus()).put("msg", Message.OUTOF_SIZE_ERROR.getMsg());
        }
        // 文件的名字
        String name=multipartFile.getOriginalFilename();
        // 取得文件后缀
        String subffix = name.substring(name.lastIndexOf("."));
        // 文件保存进来，我给他重新命名，新名字存入数据库。
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + name;
        // 文件子目录
        String sonPath = new SimpleDateFormat("yyyyMM").format(new Date());
        // 根据服务器所处平台更改文件存储位置
        if (isWindows){
            // 获取文件存放位置
            filepath = "F:\\Files\\mylog\\" + subffix.substring(1) + "\\" + sonPath + "\\";
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
        if(this.insertToDatabase(filepath, articleDto)){
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

    private Boolean insertToDatabase(String filePath, ArticleDto articleDto){
        // 插入数据库
        UserVO currentUser = userService.getUser();
        Article article = new Article();
        if (currentUser != null){
            BeanUtils.copyProperties(articleDto, article);
            // 默认不封
            article.setIsDel("0");
            // 设置创建时间
            article.setCreateTime(new Date());
            // 设置作者id为上传者的id
            article.setUserId(currentUser.getId());
            // 设置真实文件路径
            article.setFilePath(filePath);
            // 设置文章是否显示
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
