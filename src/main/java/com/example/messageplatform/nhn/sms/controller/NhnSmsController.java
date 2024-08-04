package com.example.messageplatform.nhn.sms.controller;

import com.example.messageplatform.nhn.sms.service.NhnSmsService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class NhnSmsController {


    @Autowired
    private NhnSmsService nhnSmsService;

    @PostMapping("/sendSms")
    public Map<String,Object> send(@RequestParam String content){
        Map<String,Object> result = nhnSmsService.sendSMS(content);
        return result;
    }

    @PostMapping("/sendMms")
    public Map<String,Object> send(@RequestPart String title,
                                   @RequestPart String content,
                                   @RequestPart @Nullable List<MultipartFile> files){

        List<Integer> list = null;
        if(files != null){
            list = nhnSmsService.uploadFiles(files);
        }
        Map<String,Object> result = nhnSmsService.sendMMS(title, content, list);
        return result;
    }
}
