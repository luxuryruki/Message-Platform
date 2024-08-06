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

    @GetMapping("/sender/categories")
    public Map<String,Object> getSenderCategories(){
        Map<String,Object> result = nhnKakaoService.getSenderCategories();
        return result;
    }

    @PostMapping("/sender/register")
    public Map<String,Object> registerProfile(@RequestParam String plusFriendId,
                                              @RequestParam String phoneNo,
                                              @RequestParam String categoryCode){
        Map<String,Object> result = nhnKakaoService.registerProfile(plusFriendId,phoneNo,categoryCode);
        return result;
    }

    @PostMapping("/sender/token")
    public Map<String,Object> authenticateToken(@RequestParam String plusFriendId,
                                              @RequestParam int token){

        Map<String,Object> result = nhnKakaoService.authenticateSenderToken(plusFriendId,token);
        return result;
    }

    @GetMapping("/sender/list")
    public Map<String,Object> getProfiles(){

        Map<String,Object> result = nhnKakaoService.getProfiles();
        return result;
    }

    @GetMapping("/sender")
    public Map<String,Object> getProfiles(@RequestParam String senderKey){

        Map<String,Object> result = nhnKakaoService.getProfile(senderKey);
        return result;
    }

    @DeleteMapping("/sender/delete")
    public Map<String,Object> deleteProfile(@RequestParam String senderKey){

        Map<String,Object> result = nhnKakaoService.deleteProfile(senderKey);
        return result;
    }


    @GetMapping("/template/categories")
    public Map<String,Object> getTemplateCategories(){
        Map<String,Object> result = nhnKakaoService.getTemplateCategories();
        return result;
    }
}
