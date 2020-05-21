package com.mylog.common.batch.controller;

import com.mylog.common.batch.service.PartitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job")
public class PartitionController {

    @Autowired
    PartitionService partitionService;

    @RequestMapping("/partition")
    public void partitionJob() throws Exception{
        partitionService.partitionJob();
    }


}
