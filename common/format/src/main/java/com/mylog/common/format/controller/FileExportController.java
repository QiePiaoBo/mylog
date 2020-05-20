package com.mylog.common.format.controller;

import com.mylog.common.format.service.export.FileExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 测试解析是否正确
 */
@Slf4j
@RestController
public class FileExportController {

    @Autowired
    FileExportService fileExportService;

    // 生成Excel(POI)
    @ResponseBody
    @GetMapping(value="/file_export")
    public void formDownload(HttpServletResponse response){
        log.info("FreemarkerController.formDownload");
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("Default.xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            fileExportService.export(out);
//            return "success";
        } catch(Exception e){
            e.printStackTrace();
//            return "导出信息失败";
        }
    }


}
