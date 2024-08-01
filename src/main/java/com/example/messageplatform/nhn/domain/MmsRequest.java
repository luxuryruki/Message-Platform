package com.example.messageplatform.nhn.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MmsRequest {
//    String templateId;
    String title;
    String body;
    String sendNo;
    List<String> attachFileIdList;
    List<Recipient> recipientList;
//    String requestDate;
//    String senderGroupingKey;
//    String userId;
//    String statsId;
//    String originCode;

}