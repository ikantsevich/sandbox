package com.exadel.telegrambot;

import com.exadel.telegrambot.bot.dto.InitialDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import static com.exadel.telegrambot.bot.utils.Method.SET_WEBHOOK;
import static com.exadel.telegrambot.bot.utils.Urls.*;

@SpringBootApplication
@EnableFeignClients
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
        System.out.println(restTemplate().postForObject(FULL_REQUEST + SET_WEBHOOK,
                new SetWebhook(GLOBAL + BASE_WEBHOOK), InitialDto.class));
    }

    @Bean
    private static RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
