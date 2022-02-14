package com.exadel.sandbox.bot.service.impl;

import com.exadel.sandbox.bot.dto.GetMeDto;
import com.exadel.sandbox.bot.feign.TelegramFeign;
import com.exadel.sandbox.bot.service.BotService;
import com.exadel.sandbox.bot.service.CallbackQueryHandler;
import com.exadel.sandbox.bot.service.MessageHandler;
import com.exadel.sandbox.bot.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BotServiceImpl implements BotService {
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final TelegramFeign telegramFeign;

    @Override
    public void updateHandler(Update update) {
        if (update.hasMessage())
            messageHandler.handle(update.getMessage());
        else if (update.hasCallbackQuery())
            callbackQueryHandler.handle(update.getCallbackQuery());
    }

    @Override
    public GetMeDto getMe() {
        return telegramFeign.getMe(TelegramUtils.TOKEN);
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name, List<List<String>> callbackData) {
        return null;
    }

    @Override
    public ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons) {
        return null;
    }
}
