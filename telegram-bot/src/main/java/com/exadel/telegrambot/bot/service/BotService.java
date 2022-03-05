package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.utils.Constant;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.Constant.CHOOSE_BOOKING_TYPE;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;

@RequiredArgsConstructor
@Component
public class BotService {
    private final TelegramFeign telegramFeign;
    private final KeyboardService keyboardService;
    private final HotDeskFeign hotDeskFeign;
    private final Executor executor;

    public void checkEmployee(Update update){
        Message message = getMessage(update);
        try {
           hotDeskFeign.getEmployeeByChatId(message.getChatId().toString());
        } catch (FeignException e) {
            e.printStackTrace();
            executor.sendMessage(message.getChatId(), "You are not our employee");
        }
    }

    public void getCountry(Update update){
        Message message = getMessage(update);
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), CHOOSE_COUNTRY);
        sendMessage.setReplyMarkup(keyboardService.countryMenu(getEmployeeId(message.getChatId().toString())));
        telegramFeign.sendMessage(sendMessage);
    }

    public void getCity(Update update){
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_CITY);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.cityMenu(data.substring(COUNTRIES.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getOffice(Update update){
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_OFFICE);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.officeMenu(data.substring(CITIES.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getDateType(Update update){
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_BOOKING_TYPE);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.createDateType(CHOOSE_BOOKING_TYPE + data.substring(OFFICE.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    private Long getEmployeeId(String chatId){
        return hotDeskFeign.getEmployeeByChatId(chatId).getId();
    }

    public void getMainMenuSend(Update update){
        Message message = getMessage(update);
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), MENU_TEXT);
        sendMessage.setReplyMarkup(keyboardService.homeMenu());
        telegramFeign.sendMessage(sendMessage);
    }

    public String getAndCheck(Update update){
        Message message = getMessage(update);
        EmployeeResponseDto employeeByChatId = hotDeskFeign.getEmployeeByChatId(message.getChatId().toString());
        return employeeByChatId.getTgInfo().getChatState();
    }

    public void hello(Update update){
        Message message = getMessage(update);
        telegramFeign.sendMessage(new SendMessage(message.getChatId().toString(), "Hello"));
    }

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
        final String data = update.getCallbackQuery().getData();
        Message message = getMessage(update);
        EditMessageText editMessageText = new EditMessageText(GET_DATE_TEXT);
        editMessageText.setReplyMarkup(keyboardService.createDate(date, data));
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        telegramFeign.editMessageText(editMessageText);
    }

    private Message getMessage(Update update){
        return (update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage());
    }

    public void getSeats(Update update){
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_SEAT);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getSeats(data.substring(DATE.length())));
        telegramFeign.editMessageText(editMessageText);
    }
}


