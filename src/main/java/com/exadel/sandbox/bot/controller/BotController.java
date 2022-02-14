package com.exadel.sandbox.bot.controller;

import com.exadel.sandbox.bot.dto.GetMeDto;
import com.exadel.sandbox.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("telegram")
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


}
