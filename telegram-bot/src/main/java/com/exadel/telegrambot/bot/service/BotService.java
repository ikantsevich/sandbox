package com.exadel.telegrambot.bot.service;

import com.exadel.telegrambot.bot.dto.GetMeDto;
import com.exadel.telegrambot.bot.dto.InitialDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class BotService {
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final TelegramFeign telegramFeign;
    private final HotDeskFeign hotDeskFeign;

    public void updateHandler(Update update) {
        if (update.hasMessage())
            messageHandler.handle(update.getMessage());
        else if (update.hasCallbackQuery())
            callbackQueryHandler.handle(update.getCallbackQuery());
    }

    public GetMeDto getMe() {
        return telegramFeign.getMe(TelegramUtils.TOKEN);
    }

    public InitialDto initializeBot(String url) {
        return telegramFeign.initializeBot(TelegramUtils.TOKEN, url);
    }
}
