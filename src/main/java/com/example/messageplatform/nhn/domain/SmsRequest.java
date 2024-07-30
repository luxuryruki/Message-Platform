package com.example.messageplatform.nhn.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmsRequest {
    String templateId;
    String body;
    String sendNo;
    String requestDate;
    String senderGroupingKey;
    String userId;
    String statsId;
    String originCode;

    Boolean useConversion;

    TemplateParameter templateParameter;
    List<Recipient> recipientList;
}
