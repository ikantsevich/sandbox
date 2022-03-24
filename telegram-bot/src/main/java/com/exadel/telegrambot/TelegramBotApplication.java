package com.exadel.telegrambot;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static com.exadel.telegrambot.bot.utils.TelegramUtils.BASE_WEBHOOK;
import static com.exadel.telegrambot.bot.utils.TelegramUtils.GLOBAL;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class TelegramBotApplication implements CommandLineRunner {
    private final TelegramFeign telegramFeign;

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(telegramFeign.setWebhook(GLOBAL  + "sandbox/" + BASE_WEBHOOK));
    }
}
