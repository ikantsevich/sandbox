package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.dto.GetMeDto;
import com.exadel.telegrambot.bot.dto.InitialDto;
import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.service.BotService;
import com.exadel.telegrambot.bot.service.CallbackQueryHandler;
import com.exadel.telegrambot.bot.service.MessageHandler;
import com.exadel.telegrambot.bot.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class BotServiceImpl implements BotService {
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final TelegramFeign telegramFeign;

    @Override
    public void updateHandler(Update update) {
        if (update.hasMessage()){
            messageHandler.handle(update.getMessage());
        }
        else if (update.hasCallbackQuery())
            callbackQueryHandler.handle(update.getCallbackQuery());
    }

    @Override
    public GetMeDto getMe() {
        return telegramFeign.getMe(Urls.TOKEN);
    }

    @Override
    public InitialDto initializeBot(String url) {
        return telegramFeign.initializeBot(Urls.TOKEN, url);
    }
}
