package com.exadel.telegrambot;

import com.exadel.telegrambot.bot.dto.InitialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import static com.exadel.telegrambot.bot.utils.Method.SET_WEBHOOK;
import static com.exadel.telegrambot.bot.utils.TelegramUtils.*;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
        System.out.println(restTemplate().postForObject(FULL_REQUEST + SET_WEBHOOK, new SetWebhook(GLOBAL + "sandbox/" + BASE_WEBHOOK), InitialDto.class));
    }

    @Bean
    public static RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
