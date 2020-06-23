package com.mylog.common.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 * @Date : Created in 14:24 2020/6/23
 * @Description :
 */
public class CommonFileWriter<T> extends FlatFileItemWriter<T> {

    private FileSystemResource fileSystemResource;

    public CommonFileWriter(Class clz){
        BeanWrapperFieldExtractor beanWrapperFieldExtractor = new BeanWrapperFieldExtractor();
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields){
            if (!Modifier.isStatic(field.getModifiers())){
                list.add(field.getName());
            }
        }
        String[] names = new String[list.size()];
        beanWrapperFieldExtractor.setNames(list.toArray(names));
        beanWrapperFieldExtractor.afterPropertiesSet();
        DelimitedLineAggregator lineAggregator = new DelimitedLineAggregator();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        setName(clz.getSimpleName());
        setEncoding("utf-8");
        fileSystemResource = new FileSystemResource("E:\\Files\\mylog\\mail\\"+ clz.getSimpleName() + ".csv");
        setResource(fileSystemResource);
        setLineAggregator(lineAggregator);
    }




}
