package com.exadel.telegrambot.bot.controller;

import com.exadel.telegrambot.bot.bot.Bot;
import com.exadel.telegrambot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Scanner;

import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.TelegramUtils.BASE_WEBHOOK;

@RestController
@RequestMapping(BASE_WEBHOOK)
@RequiredArgsConstructor
public class BotController{

    private final Bot bot;
    private final BotService botService;

    @PostMapping
    public void getUpdate(@RequestBody Update update) {
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            if (data.equals(SKIP)) return;
            if (data.equals(CANCEL) || data.equals(DELETE)){
                botService.deleteMessage(update);
                return;
            }
            if (data.startsWith(PREV) || data.startsWith(NEXT)) {
                botService.switchDate(update);
                return;
            }
        }
        bot.updateHandler(update);
    }
}