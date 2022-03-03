package com.exadel.telegrambot.bot.controller;

import com.exadel.telegrambot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.exadel.telegrambot.bot.utils.Urls.BASE_WEBHOOK;

@RestController
@RequestMapping(BASE_WEBHOOK)
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping()
    public void getUpdate(@RequestBody Update update) {
        botService.updateHandler(update);
    }

}