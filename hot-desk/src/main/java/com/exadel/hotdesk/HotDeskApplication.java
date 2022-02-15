package com.exadel.hotdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class HotDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotDeskApplication.class, args);
    }

}
