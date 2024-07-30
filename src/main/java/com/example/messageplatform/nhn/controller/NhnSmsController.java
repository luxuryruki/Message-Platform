package com.example.messageplatform.nhn.controller;

import com.example.messageplatform.nhn.service.NhnSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NhnSmsController {


    @Autowired
    private NhnSmsService nhnSmsService;

    @PostMapping("/sendSms")
    public Map<String,Object> send(@RequestParam String content){
        Map<String,Object> result = nhnSmsService.sendMessage(content);
        return result;
    }
}
