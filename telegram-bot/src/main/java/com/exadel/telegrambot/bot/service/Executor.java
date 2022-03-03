package com.exadel.telegrambot.bot.service;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
@RequiredArgsConstructor
public class Executor {
    private final TelegramFeign telegramFeign;

    public void sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        sendMessage(chatId, text, null, inlineKeyboardMarkup);
    }

    public void sendMessage(Long chatId, String text){
        sendMessage(chatId, text, null, null);
    }

    public void sendMessage(Long chatId, String text, ReplyKeyboardMarkup replyKeyboardMarkup) {
        sendMessage(chatId, text, replyKeyboardMarkup, null);
    }

    public void sendMessage(SendMessage sendMessage) {
        telegramFeign.sendMessage(sendMessage);
    }

    private void sendMessage(Long chatId, String text, ReplyKeyboardMarkup replyKeyboardMarkup, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        if (replyKeyboardMarkup != null)
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        if (inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        telegramFeign.sendMessage(sendMessage);
    }

    public void updateMessage(Long chatId, Integer messageId,  String text){
        updateMessage(chatId, messageId, text, null);
    }

    public void updateMessage(Long chatId, Integer messageId, String text, InlineKeyboardMarkup inlineKeyboardMarkup){
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId.toString());
        editMessageText.setMessageId(messageId);
        editMessageText.setText(text);
        if (inlineKeyboardMarkup != null)
            editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        telegramFeign.editMessageText(editMessageText);
    }
}
