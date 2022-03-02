package com.exadel.telegrambot.bot.service.impl;

import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.service.KeyboardService;
import com.exadel.telegrambot.bot.service.MessageHandler;
import com.exadel.telegrambot.bot.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
    private final TelegramFeign telegramFeign;
    private final KeyboardService keyboardService;
    private static List<List<String>> countries = new ArrayList<>();
    private static List<List<String>> cities = new ArrayList<>();
    private static List<List<String>> offices = new ArrayList<>();
    private static List<List<String>> bookingTypes = new ArrayList<>();
    private static List<List<String>> countriesRollbackData = new ArrayList<>();
    private static List<List<String>> citiesRollbackData = new ArrayList<>();
    private static List<List<String>> officesRollbackData = new ArrayList<>();

    static {
        countries.add(new ArrayList<>(Arrays.asList("England", "Russia", "Belarus")));
        countries.add(new ArrayList<>(Arrays.asList("Poland", "Georgia", "Germany")));
        countries.add(new ArrayList<>(Arrays.asList("Italy ", "Kazakhstan", "Kuwait")));

        countriesRollbackData.add(new ArrayList<>(Arrays.asList("Country:England", "Country:Russia", "Country:Belarus")));
        countriesRollbackData.add(new ArrayList<>(Arrays.asList("Country:Poland", "Country:Georgia", "Country:Germany")));
        countriesRollbackData.add(new ArrayList<>(Arrays.asList("Country:Italy ", "Country:Kazakhstan", "Country:Kuwait")));


    }


    //    This place is just for testing for now. Then there are going to be a logic
    @Override
    public void handle(Message message) {
        String text = message.getText();
        String chatId = message.getChatId().toString();
        switch (text) {
            case "/start":
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Welcome, "+message.getFrom().getFirstName());
                sendMessage.setReplyMarkup(keyboardService.getReplyForMainMenu());
                telegramFeign.sendMessage(TelegramUtils.TOKEN, sendMessage);
                break;
            case "BOOK WORKPLACE":
                SendMessage sendMessage1 = new SendMessage();
                sendMessage1.setChatId(chatId);
                sendMessage1.setText("Select Country ");
                sendMessage1.setReplyMarkup(keyboardService.getInlineKeyboard(countries,countriesRollbackData));
                telegramFeign.sendMessage(TelegramUtils.TOKEN, sendMessage1);
                break;
        }
    }
}
