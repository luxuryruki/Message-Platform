package com.example.messageplatform.nhn.sms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Recipient {
    String recipientNo;
//    String countryCode;
//    String internationalRecipientNo;
//    String recipientGroupingKey;
    Map<String,String> templateParameter;
}
