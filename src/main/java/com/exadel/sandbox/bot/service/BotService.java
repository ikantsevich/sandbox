package com.exadel.sandbox.bot.service;

import com.exadel.sandbox.bot.dto.GetMeDto;
import com.exadel.sandbox.bot.dto.InitialDto;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService {
    void updateHandler(Update update);

    GetMeDto getMe();

    InitialDto initializeBot(String url);
}
