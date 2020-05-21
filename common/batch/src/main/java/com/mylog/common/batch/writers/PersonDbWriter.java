package com.mylog.common.batch.writers;

import com.mylog.common.batch.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
public class PersonDbWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> persons) throws Exception {

        FileOutputStream fos = new FileOutputStream("E:\\reportFiles\\persons\\persons.txt");
        String content = "";
        for (Person person: persons){
            log.info(person.getName());
                content += person.getAge() + person.getName() + person.getGender() + "\n";
        }

        fos.write(content.getBytes());
        fos.flush();
        fos.close();

    }
}
