package com.example.messageplatform.nhn.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Recipient {
    String recipientNo;
    String countryCode;
    String internationalRecipientNo;
    String recipientGroupingKey;

}
