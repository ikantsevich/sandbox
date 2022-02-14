package com.exadel.sandbox.bot.service.impl;

import com.exadel.sandbox.bot.feign.TelegramFeign;
import com.exadel.sandbox.bot.service.KeyboardService;
import com.exadel.sandbox.bot.service.MessageHandler;
import com.exadel.sandbox.bot.utils.TelegramUtils;
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
            SendMessage sendMessage = new SendMessage(message.getChatId().toString(), message.getText());
            sendMessage.setReplyMarkup(keyboardService.getReplyKeyboard(List.of(List.of("Share contact", message.getText())), List.of(List.of(true, false))));
            telegramFeign.sendMessage(TelegramUtils.TOKEN, sendMessage);
        }
    }
}
