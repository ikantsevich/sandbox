package com.exadel.telegrambot.bot.bot;

import com.exadel.telegrambot.bot.service.BotService;
import com.exadel.telegrambot.bot.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_BOOKING_TYPE;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_RECURRING_TIME;

@Service
@RequiredArgsConstructor
public class Bot {
    private final BotService botService;

    public void updateHandler(Update update) {
        botService.checkEmployee(update);
        String state = botService.getAndCheck(update);
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasText()){
                String text = message.getText();
                if (text.equals(START)){
                    state = MAIN_MENU_SEND;
                } else if (text.equals(NEW_BOOKING)){
                    state = COUNTRIES;
                }
            } else if (message.hasContact()){

            }
        } else if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if (data.startsWith(COUNTRIES)){
                state = CITIES;
            } else if (data.startsWith(CITIES)){
                state = OFFICE;
            } else if (data.startsWith(OFFICE)){
                state = CHOOSE_BOOKING_TYPE;
            } else if (data.startsWith(CHOOSE_BOOKING_TYPE) && data.endsWith(ONE_DAY)){
                state = GET_DATE;
            } else if (data.endsWith(ONE_DAY)){
                state = GET_SEATS;
            } else if (data.endsWith(RECURRING)){
                state = CHOOSE_RECURRING_TIME;
            } else if (data.startsWith(CHOOSE_RECURRING_TIME)){
                state = GET_DAY_OF_WEEK;
            }
        }

        if (state.equals(SKIP_ACTION))
            return;

        switch (state){
            case MAIN_MENU_SEND -> botService.getMainMenuSend(update);
            case COUNTRIES -> botService.getCountry(update);
            case CITIES -> botService.getCity(update);
            case OFFICE -> botService.getOffice(update);
            case CHOOSE_BOOKING_TYPE -> botService.getDateType(update);
            case GET_DATE -> botService.getDate(update, LocalDate.now());
            case GET_SEATS -> botService.getSeats(update);
            case CHOOSE_RECURRING_TIME -> botService.getRecurringTime(update);
            case GET_DAY_OF_WEEK -> botService.getDayOfWeeK(update);
        }
    }
}
