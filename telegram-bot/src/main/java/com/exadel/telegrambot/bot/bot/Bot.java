package com.exadel.telegrambot.bot.bot;

import com.exadel.telegrambot.bot.service.BotService;
import com.exadel.telegrambot.bot.utils.EmployeeState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.exadel.telegrambot.bot.utils.Constant.CHOSE_NEW_SEAT;
import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_BOOKING_TYPE;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOOSE_RECURRING_TIME;
import static com.exadel.telegrambot.bot.utils.EmployeeState.CHOSE_END_DATE_FOR_UPDATE;
import static com.exadel.telegrambot.bot.utils.EmployeeState.GET_CONTINUOUS_DATE_END;
import static com.exadel.telegrambot.bot.utils.EmployeeState.NEW_DATE;
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
            } else if ((data.endsWith(ONE_DAY) && (!data.startsWith("id:"))) || text.equals(GET_CONTINUOUS_DATE_END)) {
                state = GET_SEATS;
            } else if (data.endsWith(ONE_DAY) && (data.startsWith("id:"))) {
                state = GET_DATE_ONE_DAY;
            } else if (data.endsWith(CONTINUOUS) && data.startsWith("id:")) {
                state = GET_DATE_CONTINUOUS;
            } else if (data.endsWith(RECURRING) && !data.startsWith("id")) {
                state = CHOOSE_RECURRING_TIME;
            } else if (data.startsWith(CHOOSE_RECURRING_TIME)) {
                state = GET_DAY_OF_WEEK;
            } else if (data.startsWith(GET_DAY_OF_WEEK)) {
                if (data.indexOf(RECURRING) > 0) state = CHOSE_RECURRING_DATE_FOR_UPDATE;
                else state = GET_SEATS_RECURRING;
            } else if (data.startsWith("id:")) {
                if (data.endsWith(RECURRING)) state = CHOOSE_RECURRING_TIME_FOR_UPDATE;
                else if (data.indexOf(RECURRING) > 0) state = GET_DAY_OF_WEEK_FOR_UPDATE;
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
            } else if (data.startsWith(CANCEL)) {
                state = CANCEL_BOOKING;
            } else if (data.startsWith(EDIT)) {
                state = EDIT_BOOKING;
            } else if (data.startsWith(BACK_TO_BOOKINGS)) {
                state = MY_BOOKINGS;
            } else if (data.startsWith(CONFIRM_CANCELLING)) {
                state = CONFIRM_CANCELLING;
            } else if (data.startsWith(CONFIRM_EDITING_FOR_DATE)) {
                if (data.indexOf(CHOSE_NEW_SEAT) > 0)
                    state = CONFIRM_EDITING_FOR_OFFICE;
                else
                    state = CONFIRM_EDITING_FOR_DATE;
            } else if (data.startsWith(BACK_TO_MAIN_MENU)) {
                state = BACK_TO_MAIN_MENU;
            } else if (data.startsWith(NEW_DATE)) state = NEW_DATE;
            else if (data.startsWith(DATE)) {
                if (data.endsWith(GET_CONTINUOUS_DATE_END)) state = GET_CONTINUOUS_DATE_END;
                else if (data.endsWith(CHOSE_END_DATE_FOR_UPDATE)) state = CHOSE_END_DATE_FOR_UPDATE;
                else state = CHOSE_NEW_DATE;
            } else if (data.startsWith(NEW_OFFICE)) {
                state = GET_NEW_OFFICE_FOR_UPDATE;
            } else if (data.startsWith(OFFICE_FOR_UPDATE)) {
                state = GET_NEW_SEAT_FOR_UPDATE;
            } else if (data.startsWith(CHOSE_NEW_SEAT)) {
                state = CHOSE_NEW_SEAT;
            }
        }

        switch (state) {
            case COUNTRIES -> botService.getCountry(update);
            case MY_BOOKINGS -> botService.getBookingsByEmId(update);
            case CITIES -> botService.getCity(update);
            case OFFICE -> botService.getOffice(update);
            case CHOOSE_BOOKING_TYPE -> botService.getDateType(update);
            case GET_DATE -> botService.getDate(update, LocalDate.now());
            case GET_SEATS -> botService.getSeats(update);
            case CHOOSE_RECURRING_TIME -> botService.getRecurringTime(update);
            case CHOOSE_RECURRING_TIME_FOR_UPDATE -> botService.getRecurringTimeForEdit(update);
            case CHOSE_RECURRING_DATE_FOR_UPDATE -> botService.getConfirmMenuForRecurringUpdate(update);
            case GET_DAY_OF_WEEK -> botService.getDayOfWeeK(update);
            case GET_DAY_OF_WEEK_FOR_UPDATE -> botService.getDayOfWeekForUpdate(update);
            case CHOSE_END_DATE_FOR_UPDATE -> botService.getConfirmMenuForContinuousUpdate(update);
            case GET_SEATS_RECURRING -> botService.getSeatsByRecurring(update);
            case GET_PARKING -> botService.getParking(update);
            case GET_REVIEW -> botService.getReview(update);
            case BOOKING -> botService.bookWorkPlace(update);
            case PREV_BOOKING -> botService.getPrevBooking(update);
            case NEXT_BOOKING -> botService.getNextBooking(update);
            case CANCEL_BOOKING -> botService.cancelBooking(update);
            case CONFIRM_CANCELLING -> botService.confirmedCancelling(update);
            case EDIT_BOOKING -> botService.editBooking(update);
            case NEW_DATE -> botService.editBookingDate(update);
            case GET_DATE_ONE_DAY -> botService.getDateForEditOneDay(update);
            case GET_DATE_CONTINUOUS -> botService.getStartDateForEditContinuous(update);
            case CHOSE_NEW_DATE -> botService.choseNewDate(update);
            case CONFIRM_EDITING_FOR_DATE -> botService.confirmedEditingForDate(update);
            case GET_CONTINUOUS_DATE_END -> botService.getEndDateForUpdate(update);
            case GET_NEW_OFFICE_FOR_UPDATE -> botService.getOfficeForUpdate(update);
            case GET_NEW_SEAT_FOR_UPDATE -> botService.getSeatsForUpdate(update);
            case EmployeeState.CHOSE_NEW_SEAT -> botService.getConfirmMenuForOfficeUpdate(update);
            case CONFIRM_EDITING_FOR_OFFICE -> botService.confirmedEditingForOffice(update);
            default -> botService.getMainMenuSend(update);
        }
    }
}