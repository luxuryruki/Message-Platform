package com.example.messageplatform.nhn.service;

import com.example.messageplatform.nhn.NhnSmsClient;
import com.example.messageplatform.nhn.configuration.NhnSmsConfiguration;
import com.example.messageplatform.nhn.domain.MmsRequest;
import com.example.messageplatform.nhn.domain.Recipient;
import com.example.messageplatform.nhn.domain.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<String,Object> sendMMS(String title, String content){
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


            Map<String,Object> result = nhnSmsClient.send(appKey, request);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
