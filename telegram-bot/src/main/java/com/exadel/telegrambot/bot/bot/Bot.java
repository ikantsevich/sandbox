package com.exadel.telegrambot.bot.bot;

import com.exadel.telegrambot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_BOOKING_TYPE;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_RECURRING_TIME;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;

@Service
@RequiredArgsConstructor
public class Bot {
    private final BotService botService;

    public void updateHandler(Update update) {
        if (botService.checkEmployee(update) == null) return;
        String state = botService.getAndCheck(update);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals(START)) {
                    state = MAIN_MENU_SEND;
                } else if (text.equals(NEW_BOOKING)) {
                    state = COUNTRIES;
                } else if (text.equals(MY_BOOKINGS)) {
                    state = MY_BOOKINGS;
                }
            } else if (message.hasContact()) {

            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String text = update.getCallbackQuery().getMessage().getText();
            if (data.startsWith(COUNTRIES)) {
                state = CITIES;
            } else if (data.startsWith(CITIES)) {
                state = OFFICE;
            } else if (data.startsWith(OFFICE)) {
                state = CHOOSE_BOOKING_TYPE;
            } else if ((data.startsWith(CHOOSE_BOOKING_TYPE) && (data.endsWith(ONE_DAY) || data.endsWith(CONTINUOUS))) || text.equals(GET_CONTINUOUS_DATE_BEGIN)) {
                state = GET_DATE;
            } else if (data.endsWith(ONE_DAY) || text.equals(GET_CONTINUOUS_DATE_END)) {
                state = GET_SEATS;
            } else if (data.endsWith(RECURRING)) {
                state = CHOOSE_RECURRING_TIME;
            } else if (data.startsWith(CHOOSE_RECURRING_TIME)) {
                state = GET_DAY_OF_WEEK;
            } else if (data.startsWith(GET_DAY_OF_WEEK)) {
                state = GET_SEATS_RECURRING;
            } else if (data.startsWith(GET_SEATS_RECURRING) || data.startsWith(ONE_DAY) || data.startsWith(CONTINUOUS)) {
                state = GET_PARKING;
            } else if (data.startsWith(GET_PARKING)) {
                state = GET_REVIEW;
            } else if (data.startsWith(GET_REVIEW)) {
                state = BOOKING;
            } else if (data.startsWith(PREV_BOOKING)) {
                state = PREV_BOOKING;
            } else if (data.startsWith(NEXT_BOOKING)) {
                state = NEXT_BOOKING;
            } else if (data.startsWith(DELETE)) {
                state = DELETE_BOOKING;
            } else if (data.startsWith(EDIT)) {
                state = EDIT_BOOKING;
            }
        }

        switch (state) {
            case COUNTRIES:
                botService.getCountry(update);
                break;
            case MY_BOOKINGS:
                botService.getBookingsByEmId(update);
                break;
            case CITIES:
                botService.getCity(update);
                break;
            case OFFICE:
                botService.getOffice(update);
                break;
            case CHOOSE_BOOKING_TYPE:
                botService.getDateType(update);
                break;
            case GET_DATE:
                botService.getDate(update, LocalDate.now());
                break;
            case GET_SEATS:
                botService.getSeats(update);
                break;
            case CHOOSE_RECURRING_TIME:
                botService.getRecurringTime(update);
                break;
            case GET_DAY_OF_WEEK:
                botService.getDayOfWeeK(update);
                break;
            case GET_SEATS_RECURRING:
                botService.getSeatsByRecurring(update);
                break;
            case GET_PARKING:
                botService.getParking(update);
                break;
            case GET_REVIEW:
                botService.getReview(update);
                break;
            case BOOKING:
                botService.bookWorkPlace(update);
                break;
            case PREV_BOOKING:
                botService.getPrevBooking(update);
                break;
            case NEXT_BOOKING:
                botService.getNextBooking(update);
                break;
            case EDIT_BOOKING:
                botService.editBooking(update);
                break;
            case DELETE_BOOKING:
                botService.deleteBooking(update);
                break;
            default:
                botService.getMainMenuSend(update);
                break;
        }
    }
}
