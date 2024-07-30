package com.example.messageplatform.nhn;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NhnSmsConfiguration {

    private String apiUrl;
    private String appKey;
    private String secretKey;



    public NhnSmsConfiguration() {
        Dotenv dotenv = Dotenv.load();
        this.apiUrl = dotenv.get("NHN_NOTIFICATION_SMS_API_URL");
        this.appKey = dotenv.get("NHN_NOTIFICATION_SMS_APP_KEY");
        this.secretKey = dotenv.get("NHN_NOTIFICATION_SMS_SECRET_KEY");
    }

}
