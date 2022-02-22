package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.service.KeyboardService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardServiceImpl implements KeyboardService {
    @Override
    public InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name, List<List<String>> callbackData) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();

        for (int i = 0; i < name.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < name.get(i).size(); j++) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(name.get(i).get(j));
                button.setCallbackData(callbackData.get(i).get(j));
                row.add(button);
            }
            inlineKeyboard.add(row);
        }

        inlineKeyboardMarkup.setKeyboard(inlineKeyboard);

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name, List<List<String>> callbackData, List<List<String>> urls) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();

        for (int i = 0; i < name.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < name.get(i).size(); j++) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(name.get(i).get(j));
                button.setCallbackData(callbackData.get(i).get(j));
                if (urls.get(i).get(j) != null)
                    button.setUrl(urls.get(i).get(i));
                row.add(button);
            }
            inlineKeyboard.add(row);
        }

        inlineKeyboardMarkup.setKeyboard(inlineKeyboard);

        return inlineKeyboardMarkup;
    }

    @Override
    public ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        for (List<String> button : buttons) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String s : button) keyboardRow.add(s);
            rows.add(keyboardRow);
        }

        replyKeyboardMarkup.setKeyboard(rows);

        return replyKeyboardMarkup;
    }

    @Override
    public ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
                                                List<List<Boolean>> requestContact) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboard(buttons);
        List<KeyboardRow> keyboard = replyKeyboardMarkup.getKeyboard();

        for (int i = 0; i < buttons.size(); i++) {
            KeyboardRow keyboardButtons = keyboard.get(i);
            for (int j = 0; j < buttons.get(i).size(); j++)
                keyboardButtons.get(j).setRequestContact(requestContact.get(i).get(j));
        }
        return replyKeyboardMarkup;
    }

    @Override
    public ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
                                                List<List<Boolean>> requestContact,
                                                List<List<Boolean>> requestLocation) {
        ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboard(buttons, requestContact);
        List<KeyboardRow> keyboard = keyboardMarkup.getKeyboard();

        for (int i = 0; i < buttons.size(); i++) {
            KeyboardRow keyboardButtons = keyboard.get(i);
            for (int j = 0; j < buttons.get(i).size(); j++)
                keyboardButtons.get(j).setRequestLocation(requestLocation.get(i).get(j));
        }

        return keyboardMarkup;
    }
}
