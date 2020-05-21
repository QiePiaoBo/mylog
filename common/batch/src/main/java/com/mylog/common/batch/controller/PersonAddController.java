package com.mylog.common.batch.controller;

import com.mylog.common.batch.service.PersonAddService;
import com.mylog.common.batch.service.PersonDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonAddController {

    @Autowired
    PersonAddService personAddService;

    @Autowired
    PersonDbService personDbService;

    @RequestMapping("/add")
    public void personAdd() throws Exception{
        personAddService.addPerson();
    }

    @RequestMapping("/read")
    public void personRead() throws Exception{
        personDbService.readPerson();
    }


}
