package com.exadel.telegrambot.bot.bot;

import com.exadel.telegrambot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;

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

        }

        if (state.equals(SKIP_ACTION))
            return;

        switch (state){
            case MAIN_MENU_SEND -> botService.getMainMenuSend(update);
            case COUNTRIES -> botService.getCountry(update);
        }
    }
}
