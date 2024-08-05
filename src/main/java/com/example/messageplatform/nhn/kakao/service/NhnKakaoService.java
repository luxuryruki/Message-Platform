package com.example.messageplatform.nhn.kakao.service;

import com.example.messageplatform.nhn.NhnSmsClient;
import com.example.messageplatform.nhn.kakao.NhnKakoaClient;
import com.example.messageplatform.nhn.kakao.configuration.NhnKakaoConfiguration;
import com.example.messageplatform.nhn.sms.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.sms.domain.FileUploadInfo;
import com.example.messageplatform.nhn.sms.domain.MmsRequest;
import com.example.messageplatform.nhn.sms.domain.Recipient;
import com.example.messageplatform.nhn.sms.domain.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class NhnKakaoService {


    @Autowired
    private NhnKakaoConfiguration configuration;
    @Autowired
    private NhnKakoaClient nhnKakoaClient;

    public Map<String,Object> getCategories(){
        try{
            String appKey = configuration.getAppKey();

            Map<String,Object> result = nhnKakoaClient.getCategories(appKey);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
