package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.service.KeyboardService;
import com.exadel.telegrambot.bot.service.MessageHandler;
import com.exadel.telegrambot.bot.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
    private final TelegramFeign telegramFeign;
    private final KeyboardService keyboardService;


    //    This place is just for testing for now. Then there are going to be a logic
    @Override
    public void handle(Message message) {
        System.out.println(message.getText());
        if (message.getText() != null) {
            telegramFeign.deleteMessage(TelegramUtils.TOKEN, message.getChatId().toString(), message.getMessageId());
        }
    }
}