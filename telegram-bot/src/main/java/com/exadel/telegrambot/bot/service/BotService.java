package com.exadel.telegrambot.bot.service;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.exadel.telegrambot.bot.utils.Constant.*;

@RequiredArgsConstructor
@Component
public class BotService {
    private final TelegramFeign telegramFeign;
    private final KeyboardService keyboardService;

    public void deleteMessage(Update update){
        Message message = getMessage(update);
        telegramFeign.deleteMessage(message.getChatId().toString(), message.getMessageId());
    }

    public void switchDate(Update update){
        final CallbackQuery callbackQuery = update.getCallbackQuery();
        boolean isPrev = callbackQuery.getData().startsWith(PREV);
        LocalDate date = LocalDate.parse(callbackQuery.getData().substring(isPrev ? PREV.length() : NEXT.length()));
        getDate(update, isPrev ? date.minusMonths(1) : date.plusMonths(1));
    }

    public void getDate(Update update, LocalDate date) {
        Message message = getMessage(update);
        EditMessageText editMessageText = new EditMessageText(GET_DATE_TEXT);
        editMessageText.setReplyMarkup(keyboardService.createDate(date));
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        telegramFeign.editMessageText(editMessageText);
    }

    private Message getMessage(Update update){
        return update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
    }
}
