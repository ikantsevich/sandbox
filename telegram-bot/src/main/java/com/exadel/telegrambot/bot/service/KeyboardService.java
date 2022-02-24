package com.exadel.telegrambot.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;


public interface KeyboardService {
    InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name,
                                           List<List<String>> callbackData);

    InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name,
                                           List<List<String>> callbackData,
                                           List<List<String>> urls);

    ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons);

    ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
                                         List<List<Boolean>> requestContact);

    ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
                                         List<List<Boolean>> requestContact,
                                         List<List<Boolean>> requestLocation);

    ReplyKeyboardMarkup getReplyForMainMenu();
}
