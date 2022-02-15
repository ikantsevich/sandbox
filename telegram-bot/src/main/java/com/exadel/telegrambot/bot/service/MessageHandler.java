package com.exadel.telegrambot.bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {
    void handle(Message message);
}
