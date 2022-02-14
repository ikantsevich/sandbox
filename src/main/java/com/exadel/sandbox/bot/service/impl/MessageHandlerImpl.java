package com.exadel.sandbox.bot.service.impl;

import com.exadel.sandbox.bot.feign.TelegramFeign;
import com.exadel.sandbox.bot.service.MessageHandler;
import com.exadel.sandbox.bot.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
    private final TelegramFeign telegramFeign;

    @Override
    public void handle(Message message) {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), message.getText());

        telegramFeign.sendMessage(TelegramUtils.TOKEN, sendMessage);
    }
}
