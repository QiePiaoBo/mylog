package com.mylog.common.batch.itemProcessors;

import com.mylog.common.batch.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @author dylan
 * @date 20200513
 * @description
 * csv文件数据校验及处理
 * 只需要实现itemProcessor接口 重写process方法，输入的参数是从ItemReader读取的数据，返回的数据给ItemWriter
 */
public class CvsItemProcessor extends ValidatingItemProcessor<Person> implements ItemProcessor<Person, Person> {
    private Logger logger = LoggerFactory.getLogger(CvsItemProcessor.class);

    @Override
    public Person process(Person item) throws ValidationException {
        // 执行super.process才能调用自定义的校验器
        logger.info("process start validating ...");
        super.process(item);

        // 数据处理，比如将中文性别设置为M/F
        if ("男".equals(item.getGender())){
            item.setGender("M");
        }else {
            item.setGender("F");
        }
        logger.info("processor end validation...");

        return item;
    }
}
