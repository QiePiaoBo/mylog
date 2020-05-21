package com.mylog.common.batch.controller;

import com.mylog.common.batch.service.NormalService;
import com.mylog.common.batch.service.PartitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job")
public class PartitionController {

    @Autowired
    private PartitionService partitionService;

    @Autowired
    private NormalService normalService;

    @RequestMapping("/partition")
    public void partitionJob() throws Exception{
        partitionService.partitionJob();
    }
    @RequestMapping("/normal")
    public void normalJob() throws Exception{
        normalService.userDbService();
    }


}
