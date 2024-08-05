package com.example.messageplatform.nhn.kakao.service;

import com.example.messageplatform.nhn.NhnSmsClient;
import com.example.messageplatform.nhn.kakao.NhnKakoaClient;
import com.example.messageplatform.nhn.kakao.configuration.NhnKakaoConfiguration;
import com.example.messageplatform.nhn.kakao.domain.SenderProfile;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NhnKakaoService {


    @Autowired
    private NhnKakaoConfiguration configuration;
    @Autowired
    private NhnKakoaClient nhnKakoaClient;

    public Map<String,Object> registerProfile(String plusFriendId,String phoneNo,String categoryCode){
        try {
            String appKey = configuration.getAppKey();
            SenderProfile senderProfile = new SenderProfile();
            senderProfile.setPlusFriendId(plusFriendId);
            senderProfile.setPhoneNo(phoneNo);
            senderProfile.setCategoryCode(categoryCode);
            Map<String,Object> response = nhnKakoaClient.registerProfile(appKey,senderProfile);
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Map<String,Object> getSenderCategories(){
        try{
            String appKey = configuration.getAppKey();

            Map<String,Object> response = nhnKakoaClient.getSenderCategories(appKey);
            List<Map<String,Object>> categories =(List<Map<String,Object>>) response.get("categories");
            List<Map<String,Object>> result = convertToFlatList(categories);
            response.put("categories",result);
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Map<String,Object> getTemplateCategories(){
        try{
            String appKey = configuration.getAppKey();

            Map<String,Object> response = nhnKakoaClient.getTemplateCategories(appKey);
//            List<Map<String,Object>> categories =(List<Map<String,Object>>) response.get("categories");
//            List<Map<String,Object>> result = convertToFlatList(categories);
//            response.put("categories",result);
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private List<Map<String,Object>> convertToFlatList(List<Map<String,Object>> items){
        List<Map<String, Object>> flatList = new ArrayList<>();
        items.forEach(item -> pathBasedEncoding(item, new StringBuilder(), new StringBuilder(), flatList));

        return flatList;
    }

    private Map<String,Object> pathBasedEncoding(Map<String,Object> map,StringBuilder parentName, StringBuilder parentPath, List<Map<String, Object>> flatList) {
        String code = (String)map.get("code");
        String name = (String)map.get("name");

        StringBuilder currentPath = new StringBuilder(parentPath).append(code);
        StringBuilder currentName = parentName.length() > 0 ? new StringBuilder(parentName).append(",").append(name) : new StringBuilder(name);

        Map<String,Object> result = new HashMap<>();
        result.put("code",currentPath.toString());
        result.put("name",currentName.toString());

        List<Map<String,Object>> subList = (List<Map<String,Object>>) map.get("subCategories");
        if(subList != null){
            subList.forEach(item -> pathBasedEncoding(item, currentName,currentPath, flatList));
        } else {
            flatList.add(result);
        }

        return result;
    }

}
