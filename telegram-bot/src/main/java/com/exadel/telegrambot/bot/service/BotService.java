package com.exadel.telegrambot.bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService {
    void updateHandler(Update update);

}
