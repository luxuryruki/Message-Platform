package com.example.messageplatform.nhn.sms.service;

import com.example.messageplatform.nhn.NhnSmsClient;
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

    public Map<String,Object> sendMMS(String title, String content, List<Integer> attachFileIdList){
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

    public List<Integer> uploadFiles(List<MultipartFile> files){
        List<Integer> list = new ArrayList<>();

        String dirPath = "uploads";

        File directory = new File(dirPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        for(MultipartFile file : files){
            try {
                String appKey = configuration.getAppKey();
                String fileName = file.getOriginalFilename();

                // set file path
                Path filePath = Paths.get(dirPath + fileName);
                // save file
                Files.write(filePath, file.getBytes());

                // encoding
                byte[] fileBytes = Files.readAllBytes(filePath);
                String base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);

                FileUploadInfo request = new FileUploadInfo();
                request.setFileName(fileName);
                request.setCreateUser("uploader");
                request.setFileBody(base64EncodedFile);

                Map<String,Object> result = nhnSmsClient.uploadFile(appKey, request);
                Map<String,Object> body = (Map<String,Object>)result.get("body");
                Map<String, Object> data = (Map<String, Object>) body.get("data");
                list.add((Integer) data.get("fileId"));
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
