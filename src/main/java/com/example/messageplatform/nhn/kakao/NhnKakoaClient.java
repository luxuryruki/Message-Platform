package com.example.messageplatform.nhn.kakao;

import com.example.messageplatform.nhn.kakao.configuration.NhnKakaoConfiguration;
import com.example.messageplatform.nhn.sms.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.sms.domain.FileUploadInfo;
import com.example.messageplatform.nhn.sms.domain.MmsRequest;
import com.example.messageplatform.nhn.sms.domain.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "nhnKakaoClient",url ="${nhn.alimtalk.api-url-prefix}", configuration = NhnKakaoConfiguration.class)
public interface NhnKakoaClient {

    @GetMapping("/alimtalk/v2.3/appkeys/{appKey}/sender/categories")
    Map<String,Object> getCategories(@PathVariable("appKey") String appKey);

}
