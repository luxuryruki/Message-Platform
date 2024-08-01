package com.example.messageplatform.nhn.configuration;

import feign.RequestInterceptor;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NhnSmsConfiguration {

    private String apiUrl;
    private String appKey;
    private String secretKey;
    private String testRecipient;
    private String testSender;



    public NhnSmsConfiguration() {
        Dotenv dotenv = Dotenv.load();
        this.apiUrl = dotenv.get("NHN_NOTIFICATION_SMS_API_URL");
        this.appKey = dotenv.get("NHN_NOTIFICATION_SMS_APP_KEY");
        this.secretKey = dotenv.get("NHN_NOTIFICATION_SMS_SECRET_KEY");
        this.testRecipient = dotenv.get("NHN_NOTIFICATION_SMS_TEST_RECIPIENT");
        this.testSender = dotenv.get("NHN_NOTIFICATION_SMS_TEST_SENDER");
    }

    @Bean
    public RequestInterceptor pushRequestInterceptor() {
        return template -> {
            String url = template.request().url();
//            if(url.contains("/mms")){
//                template.header("Content-Type", "multipart/form-data");
//            }else {
//                template.header("Content-Type", "application/json");
//            }
            template.header("Content-Type", "application/json");
            template.header("X-Secret-Key", secretKey);
            template.header("Accept", "application/json");
        };
    }
}
