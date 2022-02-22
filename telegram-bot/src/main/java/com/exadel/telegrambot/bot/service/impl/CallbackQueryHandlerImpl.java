package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.service.CallbackQueryHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackQueryHandlerImpl implements CallbackQueryHandler {
    @Override
    public void handle(CallbackQuery callbackQuery) {

    }
}
