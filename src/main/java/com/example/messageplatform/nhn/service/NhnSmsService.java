package com.example.messageplatform.nhn.service;

import com.example.messageplatform.nhn.NhnSmsClient;
import com.example.messageplatform.nhn.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.domain.MmsRequest;
import com.example.messageplatform.nhn.domain.Recipient;
import com.example.messageplatform.nhn.domain.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NhnSmsService {


    @Autowired
    private NhnSmsConfiguration configuration;
    @Autowired
    private NhnSmsClient nhnSmsClient;

    public Map<String,Object> sendSMS(String content){
        try{
            String appKey = configuration.getAppKey();

            Recipient recipient = new Recipient();
            recipient.setRecipientNo(configuration.getTestRecipient());

            List<Recipient> list = new ArrayList<>();
            list.add(recipient);


            SmsRequest request = new SmsRequest();
            request.setBody(content);
            request.setSendNo(configuration.getTestSender());
            request.setRecipientList(list);


            Map<String,Object> result = nhnSmsClient.send(appKey, request);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Map<String,Object> sendMMS(String title, String content, List<String> attachFileIdList){
        try{
            String appKey = configuration.getAppKey();

            Recipient recipient = new Recipient();
            recipient.setRecipientNo(configuration.getTestRecipient());

            List<Recipient> list = new ArrayList<>();
            list.add(recipient);


            MmsRequest request = new MmsRequest();
            request.setTitle(title);
            request.setBody(content);
            request.setSendNo(configuration.getTestSender());
            request.setRecipientList(list);

            if(attachFileIdList != null){
              request.setAttachFileIdList(attachFileIdList);
            }


            Map<String,Object> result = nhnSmsClient.send(appKey, request);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<String> uploadFiles(List<MultipartFile> files){
        List<String> list = new ArrayList<>();

        String dirPath = "uploads";

        File directory = new File(dirPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        for(MultipartFile file : files){

            list.add(uploadFile(file,dirPath));
        }
        return list;
    }

    public String uploadFile(MultipartFile file, String dirPath){
        try {
            if(dirPath == null){
                dirPath = "uploads";
            }
            File directory = new File(dirPath);
            if(!directory.exists()){
                directory.mkdirs();
            }
            String fileName = file.getOriginalFilename();

            // set file path
            Path filePath = Paths.get(dirPath + fileName);
            // save file
            Files.write(filePath, file.getBytes());

            return fileName.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
