package com.example.messageplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MessageplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageplatformApplication.class, args);
    }

}
