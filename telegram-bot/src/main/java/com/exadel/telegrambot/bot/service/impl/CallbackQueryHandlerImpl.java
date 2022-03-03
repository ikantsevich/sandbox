package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.service.CallbackQueryHandler;
import com.exadel.telegrambot.bot.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandlerImpl implements CallbackQueryHandler {
    private final KeyboardService keyboardService;
    private final TelegramFeign telegramFeign;
    private static List<List<String>> cities = new ArrayList<>();
    private static List<List<String>> offices = new ArrayList<>();
    private static List<List<String>> bookingTypes = new ArrayList<>();
    private static List<List<String>> citiesRollbackData = new ArrayList<>();
    private static List<List<String>> officesRollbackData = new ArrayList<>();

    static {
        cities.add(new ArrayList<>(Arrays.asList("Tashkent", "Samarkand", "Bukhara")));
        cities.add(new ArrayList<>(Arrays.asList("Navoi", "Jizzakh", "Ferghana")));
        cities.add(new ArrayList<>(Arrays.asList("Namangan", "Sirdarya", "Surkhandarya")));

        citiesRollbackData.add(new ArrayList<>(Arrays.asList("City:Tashkent", "City:Samarkand", "City:Bukhara")));
        citiesRollbackData.add(new ArrayList<>(Arrays.asList("City:Navoi", "City:Jizzakh", "City:Ferghana")));
        citiesRollbackData.add(new ArrayList<>(Arrays.asList("City:Namangan", "City:Sirdarya", "City:Surkhandarya")));

        offices.add(new ArrayList<>(Arrays.asList("Office1", "Office2", "Office3")));
        offices.add(new ArrayList<>(Arrays.asList("Office4", "Office5", "Office6")));
        offices.add(new ArrayList<>(Arrays.asList("Office7", "Office8", "Office9")));

        officesRollbackData.add(new ArrayList<>(Arrays.asList("Office:Office1", "Office:Office2", "Office:Office3")));
        officesRollbackData.add(new ArrayList<>(Arrays.asList("Office:Office4", "Office:Office5", "Office:Office6")));
        officesRollbackData.add(new ArrayList<>(Arrays.asList("Office:Office7", "Office:Office8", "Office:Office9")));

        bookingTypes.add(new ArrayList<>(Arrays.asList("One-Day", "Continuous", "Recurring")));
    }

    @Override
    public void handle(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        String chatId = callbackQuery.getMessage().getChatId().toString();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(messageId);
        editMessageText.setChatId(chatId);
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setMessageId(messageId);
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(messageId);
        if (data.startsWith("Country:")) {
            editMessageText.setText("Select City");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.getInlineKeyboard(cities, citiesRollbackData));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        } else if (data.startsWith("City:")) {
            editMessageText.setText("Select Office");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.getInlineKeyboard(offices, officesRollbackData));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        } else if (data.startsWith("Office")){
            editMessageText.setText("Select BookingType");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.getInlineKeyboard(bookingTypes, bookingTypes));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        } else if (data.startsWith("One-Day")){
            editMessageText.setText("Select day");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.createDate(LocalDate.now()));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        } else if (data.startsWith("Continuous")){
            editMessageText.setText("Select beginning day of your booking");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.createDate(LocalDate.now()));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        } else if (data.startsWith("Recurring")){
            editMessageText.setText("Select beginning day of your booking");
            editMessageReplyMarkup.setReplyMarkup(keyboardService.createDate(LocalDate.now()));
            telegramFeign.editMessageText(editMessageText);
            telegramFeign.editMessageReplyMarkup(editMessageReplyMarkup);
        }

    }


}
