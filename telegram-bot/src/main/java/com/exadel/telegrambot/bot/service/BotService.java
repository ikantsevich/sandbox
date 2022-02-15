package com.exadel.telegrambot.bot.service;

import com.exadel.telegrambot.bot.dto.GetMeDto;
import com.exadel.telegrambot.bot.dto.InitialDto;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService {
    void updateHandler(Update update);

    GetMeDto getMe();

    InitialDto initializeBot(String url);
}
