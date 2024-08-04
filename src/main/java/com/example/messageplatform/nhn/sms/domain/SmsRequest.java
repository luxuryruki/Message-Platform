package com.example.messageplatform.nhn.sms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmsRequest {
//    String templateId;
    String body;
    String sendNo;
    List<Recipient> recipientList;
//    String requestDate;
//    String senderGroupingKey;
//    String userId;
//    String statsId;
//    String originCode;
//
//    Boolean useConversion;
//


}
