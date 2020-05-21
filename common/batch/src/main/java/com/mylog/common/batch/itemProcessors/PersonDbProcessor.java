package com.mylog.common.batch.itemProcessors;

import com.mylog.common.batch.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

@Slf4j
public class PersonDbProcessor extends ValidatingItemProcessor<Person> implements ItemProcessor<Person, Person> {


    @Override
    public Person process(Person person) throws ValidationException {
        // 执行super.process才能调用自定义的校验器
        log.info("数据处理开始 ...");
        super.process(person);

        // 数据处理，M/F显示为男女
        if ("M".equals(person.getGender())){
            person.setGender("男");
        }else {
            person.setGender("女");
        }

        log.info("数据处理结束");

        return person;
    }
}
