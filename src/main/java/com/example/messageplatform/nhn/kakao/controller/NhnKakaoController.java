package com.example.messageplatform.nhn.kakao.controller;

import com.example.messageplatform.nhn.kakao.service.NhnKakaoService;
import com.example.messageplatform.nhn.sms.service.NhnSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NhnKakaoController {


    @Autowired
    private NhnKakaoService nhnKakaoService;

    @GetMapping("/categories")
    public Map<String,Object> getCategories(){
        Map<String,Object> result = nhnKakaoService.getCategories();
        return result;
    }
}
