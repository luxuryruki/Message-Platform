package com.example.messageplatform.nhn.kakao;

import com.example.messageplatform.nhn.kakao.configuration.NhnKakaoConfiguration;
import com.example.messageplatform.nhn.kakao.domain.SenderProfile;
import com.example.messageplatform.nhn.sms.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.sms.domain.FileUploadInfo;
import com.example.messageplatform.nhn.sms.domain.MmsRequest;
import com.example.messageplatform.nhn.sms.domain.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "nhnKakaoClient",url ="${nhn.alimtalk.api-url-prefix}", configuration = NhnKakaoConfiguration.class)
public interface NhnKakoaClient {

    @GetMapping("/alimtalk/v2.3/appkeys/{appKey}/sender/categories")
    Map<String,Object> getSenderCategories(@PathVariable("appKey") String appKey);


    @PostMapping("/alimtalk/v2.3/appkeys/{appKey}/senders")
    Map<String,Object> registerProfile(@PathVariable("appKey") String appKey, @RequestBody SenderProfile senderProfile);

    @PostMapping("/alimtalk/v2.3/appkeys/{appKey}/sender/token")
    Map<String,Object> authenticateSenderToken(@PathVariable("appKey") String appKey, @RequestBody Map<String,Object> data);

    @GetMapping("/alimtalk/v2.3/appkeys/{appKey}/template/categories")
    Map<String,Object> getTemplateCategories(@PathVariable("appKey") String appKey);


    @GetMapping("/alimtalk/v2.3/appkeys/{appKey}/senders")
    Map<String,Object> getProfiles(@PathVariable("appKey") String appKey);
    @GetMapping("/alimtalk/v2.3/appkeys/{appKey}/senders/{senderKey}")
    Map<String,Object> getProfile(@PathVariable("appKey") String appKey,@PathVariable("senderKey") String senderKey);
    @DeleteMapping("/alimtalk/v2.3/appkeys/{appKey}/senders/{senderKey}")
    Map<String,Object> deleteProfile(@PathVariable("appKey") String appKey,@PathVariable("senderKey") String senderKey);


    @PostMapping("/alimtalk/v2.3/appkeys/{appKey}/messages")
    Map<String,Object> sendAlimTalk(@PathVariable("appKey") String appKey, @RequestBody Map<String,Object> data);
}
