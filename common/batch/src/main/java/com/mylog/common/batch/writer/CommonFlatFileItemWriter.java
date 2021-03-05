package com.mylog.common.batch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dylan
 * @Date : Created in 15:15 2020/10/13
 * @Description :
 * @Function :
 */
public class CommonFlatFileItemWriter<T> extends FlatFileItemWriter<T> {

    private FileSystemResource fileSystemResource;

    public CommonFlatFileItemWriter(Class clz) {
        BeanWrapperFieldExtractor beanWrapperFieldExtractor = new BeanWrapperFieldExtractor();
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (java.lang.reflect.Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
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
        setEncoding(CommonConstants.ENCODING_READ);
        fileSystemResource = new FileSystemResource("F:\\data\\batch_template\\mail\\"+ clz.getSimpleName() + new SimpleDateFormat("yyyyMMddhh").format(new Date()) + ".csv");
        setResource(fileSystemResource);
        setLineAggregator(lineAggregator);
    }
}