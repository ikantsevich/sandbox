package com.exadel.telegrambot.bot.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackQueryHandler {
    void handle(CallbackQuery callbackQuery);
}
