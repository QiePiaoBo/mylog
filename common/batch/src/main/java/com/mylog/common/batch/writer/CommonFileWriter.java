package com.mylog.common.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.FileSystemResource;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dylan
 * @Date : Created in 14:24 2020/6/23
 * @Description : 生成 年月日时/名字-分秒.csv文件
 */
@RefreshScope
public class CommonFileWriter<T> extends FlatFileItemWriter<T> {

    @Value("${filePath}")
    private String filePath;
    public CommonFileWriter(Class clz){
        BeanWrapperFieldExtractor beanWrapperFieldExtractor = new BeanWrapperFieldExtractor();
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields){
            if (!Modifier.isStatic(field.getModifiers())){
                list.add(field.getName());
            }
        }
        System.out.println(filePath);
        String[] names = new String[list.size()];
        beanWrapperFieldExtractor.setNames(list.toArray(names));
        beanWrapperFieldExtractor.afterPropertiesSet();
        DelimitedLineAggregator lineAggregator = new DelimitedLineAggregator();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        setName(clz.getSimpleName());
        setEncoding("utf-8");
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")){
            FileSystemResource fileSystemResource = new FileSystemResource("F:\\Files\\mylog\\mail\\" +
                    new SimpleDateFormat("yyyyMMddHH").format(new Date()) +
                    "\\" + clz.getSimpleName() + "-" +
                    new SimpleDateFormat("mmss").format(new Date()) +
                    ".csv");
            setResource(fileSystemResource);
            setLineAggregator(lineAggregator);
        }else {
            FileSystemResource fileSystemResource = new FileSystemResource("/var/www/springcloud/data/" +
                    new SimpleDateFormat("yyyyMMddHH").format(new Date()) +
                    "\\" + clz.getSimpleName() + "-" +
                    new SimpleDateFormat("mmss").format(new Date()) +
                    ".csv");
            setResource(fileSystemResource);
            setLineAggregator(lineAggregator);
        }
    }
}
