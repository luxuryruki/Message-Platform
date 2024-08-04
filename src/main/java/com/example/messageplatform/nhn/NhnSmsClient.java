package com.example.messageplatform.nhn;

import com.example.messageplatform.nhn.sms.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.sms.domain.FileUploadInfo;
import com.example.messageplatform.nhn.sms.domain.MmsRequest;
import com.example.messageplatform.nhn.sms.domain.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "nhnSmsClient",url ="${nhn.sms.api-url-prefix}", configuration = NhnSmsConfiguration.class)
public interface NhnSmsClient {

    @PostMapping("/sms/v3.0/appKeys/{appKey}/sender/sms")
    Map<String,Object> send(@PathVariable("appKey") String appKey, @RequestBody SmsRequest smsRequest);

    @PostMapping("/sms/v3.0/appKeys/{appKey}/sender/mms")
    Map<String,Object> send(@PathVariable("appKey") String appKey, @RequestBody MmsRequest mmsRequest);

    @PostMapping("/sms/v3.0/appKeys/{appKey}/attachfile/binaryUpload")
    Map<String,Object> uploadFile(@PathVariable("appKey") String appKey, @RequestBody FileUploadInfo fileUploadInfo);
}
