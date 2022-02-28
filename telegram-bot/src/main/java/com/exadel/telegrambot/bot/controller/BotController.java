package com.exadel.telegrambot.bot.controller;

import com.exadel.telegrambot.bot.dto.GetMeDto;
import com.exadel.telegrambot.bot.dto.InitialDto;
import com.exadel.telegrambot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.exadel.telegrambot.bot.utils.Urls.*;

@RestController
@RequestMapping(BASE_WEBHOOK)
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping()
    public void getUpdate(@RequestBody Update update) {
        botService.updateHandler(update);
    }

    @GetMapping("getMe")
    public GetMeDto getMe() {
        return botService.getMe();
    }

    @PostMapping("initialize")
    public InitialDto initialize(@RequestParam String url) {
        return botService.initializeBot(url);
    }
}