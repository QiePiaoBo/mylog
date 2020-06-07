package com.mylog.common.batch.controller;

import com.mylog.common.batch.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    MailService mailService;

    @RequestMapping("/toQQ")
    public void sendToqq(){
        mailService.sendSimpleMailMessage("1171357812@qq.com",
                "支付","<h1>Hello do you see me?</h1><br />支付宝到账10000.0元");
    }
    @RequestMapping("/withFile")
    public void withFile(){
        mailService.sendMimeMessage("1171357812@qq.com",
                "支付","<h1>Hello do you see me?</h1>","E:\\projects\\files\\springbatch\\jobFlow.jpg");
    }

    @RequestMapping("/staticResource")
    public void sendPicMessage() throws FileNotFoundException {
        String htmlStr = "<html><body>测试：图片1 <br> <img src=\'cid:pic1\'/> <br>图片2 <br> <img src=\'cid:pic2\'/></body></html>";
        Map<String, String> rscIdMap = new HashMap<>(2);
        rscIdMap.put("pic1", ResourceUtils.getFile("classpath:pic01.png").getAbsolutePath());
        rscIdMap.put("pic2", ResourceUtils.getFile("classpath:pic02.png").getAbsolutePath());
        mailService.sendMimeMessge("1171357812@qq.com", "测试", htmlStr, rscIdMap);
    }
}
