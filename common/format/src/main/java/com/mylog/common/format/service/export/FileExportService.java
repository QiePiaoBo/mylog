package com.mylog.common.format.service.export;

import com.alibaba.fastjson.JSONObject;
import com.mylog.common.format.service.factory.JsonFactory;
import com.mylog.common.format.service.parse.ParseService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * 下载服务中心
 * @author Dylan
 */
@Service
public class FileExportService {

    @Autowired
    ParseService parseService;

    /**
     * POI生成excel
     * @param out
     * @throws Exception
     */
    public void export(ServletOutputStream out) throws Exception {
        JSONObject dataJson = parseService.getEndJson();
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
            // 表头即第一行数据
            List heads = JSONObject.parseArray(dataJson.get("cardTitle").toString());
            HSSFCell hssfCell = null;
            for (int i = 0; i < heads.size(); i++) {
                //列索引从0开始
                hssfCell = row.createCell(i);
                //列名1
                hssfCell.setCellValue(heads.get(i).toString());
                //列居中显示
                hssfCell.setCellStyle(hssfCellStyle);
            }
            // 第五步，写入实体数据 这里我把list当做数据库
            List contentList = JSONObject.parseArray(dataJson.get("cardContent").toString());
            for (int i = 0; i < contentList.size(); i++) {
                // 第六步 新建内容的单元格并设置值
                row = hssfSheet.createRow(i + 1);
                // 获取根据contentList的下标获取每一个小list
                List content = JSONObject.parseArray(contentList.get(i).toString());
                for (int j = 0; j < content.size(); j ++){
                    row.createCell(j).setCellValue(content.get(j).toString());
                }
            }
            try {
                // 输出到客户端
                workbook.write(out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
}
