package com.exadel.sandbox.bot.service;

import com.exadel.sandbox.bot.dto.GetMeDto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

public interface BotService {
    void updateHandler(Update update);

    GetMeDto getMe();

    InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name, List<List<String>> callbackData);

    ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons);
}
