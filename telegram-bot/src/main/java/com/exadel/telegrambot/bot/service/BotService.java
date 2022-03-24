package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.feign.TelegramFeign;
import com.exadel.telegrambot.bot.utils.EmployeeState;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.exadel.telegrambot.bot.utils.Constant.CHOOSE_BOOKING_TYPE;
import static com.exadel.telegrambot.bot.utils.Constant.CHOOSE_RECURRING_TIME;
import static com.exadel.telegrambot.bot.utils.Constant.CHOSE_END_DATE_FOR_UPDATE;
import static com.exadel.telegrambot.bot.utils.Constant.NEW_DATE;
import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.GET_CONTINUOUS_DATE_END;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;

@RequiredArgsConstructor
@Component
public class BotService {
    private final TelegramFeign telegramFeign;
    private final KeyboardService keyboardService;
    private final HotDeskFeign hotDeskFeign;
    private final ModelMapper modelMapper;

    public EmployeeResponseDto checkEmployee(Update update) {
        Message message = getMessage(update);
        try {
            return hotDeskFeign.getEmployeeByChatId(message.getChatId().toString());
        } catch (FeignException e) {
            e.printStackTrace();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(YOU_NOT_EMPLOYEE);
            sendMessage.setChatId(message.getChatId().toString());
            telegramFeign.sendMessage(sendMessage);
            return null;
        }
    }

    public void getCountry(Update update) {
        Message message = getMessage(update);
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), CHOOSE_COUNTRY);
        sendMessage.setReplyMarkup(keyboardService.countryMenu(getEmployeeId(message.getChatId().toString())));
        telegramFeign.sendMessage(sendMessage);
    }

    public void getBookingsByEmId(Update update) {
        Message message = getMessage(update);
        if (update.hasCallbackQuery()) deleteMessage(update);
        List<BookingResponseDto> bookings = hotDeskFeign.findBookingsByEmId(getEmployeeId(message.getChatId().toString()));
        SendMessage sendMessage = new SendMessage();
        if (bookings.size() == 0) {
            sendMessage.setText("Ooops, you don't have any bookings");
            sendMessage.setChatId(message.getChatId().toString());
            telegramFeign.sendMessage(sendMessage);
            return;
        }

        BookingResponseDto bookingResponseDto = bookings.get(0);
        sendMessage = new SendMessage(message.getChatId().toString(), textBuilderForBooking(bookingResponseDto));
        if (bookings.size() > 1)
            sendMessage.setReplyMarkup(keyboardService.myBookingInlineKeyboard(bookingResponseDto.getId().toString(), false, true));
        else
            sendMessage.setReplyMarkup(keyboardService.myBookingInlineKeyboard(bookingResponseDto.getId().toString(), false, false));
        telegramFeign.sendMessage(sendMessage);
    }

    private String textBuilderForBooking(BookingResponseDto bookingResponseDto) {
        return "\nCreated at: " + bookingResponseDto.getCreated() + "\nCountry: " + bookingResponseDto.getOffice().getAddress().getCountry() + "\nCity: " + bookingResponseDto.getOffice().getAddress().getCity() + "\nStreet: " + bookingResponseDto.getOffice().getAddress().getStreet() + "\nBuilding number: " + bookingResponseDto.getOffice().getAddress().getBuildingNum() + "\nFloor Number: " + bookingResponseDto.getFloorNumber();
    }

    public void getCity(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_CITY);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.cityMenu(data.substring(COUNTRIES.length() + 1)));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getOffice(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_OFFICE);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.officeMenu(data.substring(CITIES.length() + 1)));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getDateType(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_BOOKING_TYPE);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.createDateType(EmployeeState.CHOOSE_BOOKING_TYPE + data.substring(OFFICE.length() + 1)));
        telegramFeign.editMessageText(editMessageText);
    }

    private Long getEmployeeId(String chatId) {
        return hotDeskFeign.getEmployeeByChatId(chatId).getId();
    }

    public void getMainMenuSend(Update update) {
        Message message = getMessage(update);
        if (update.hasCallbackQuery()) deleteMessage(update);
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), MENU_TEXT + message.getFrom().getFirstName());
        sendMessage.setReplyMarkup(keyboardService.homeMenu());
        telegramFeign.sendMessage(sendMessage);
    }

    public String getAndCheck(Update update) {
        EmployeeResponseDto employeeByChatId = checkEmployee(update);
        return employeeByChatId == null ? SKIP_ACTION : employeeByChatId.getTgInfo().getChatState();
    }

    public void hello(Update update) {
        Message message = getMessage(update);
        telegramFeign.sendMessage(new SendMessage(message.getChatId().toString(), "Hello"));
    }

    public void deleteMessage(Update update) {
        Message message = getMessage(update);
        telegramFeign.deleteMessage(new DeleteMessage(message.getChatId().toString(), message.getMessageId()));
    }

    public void switchDate(Update update) {
        String data = update.getCallbackQuery().getData();
        boolean isPrev = data.contains(PREV);
        String substring = data.substring(0, data.length() - 2);
        LocalDate date = LocalDate.parse(substring.substring(substring.length()-10));
        getDate(update, isPrev ? date.minusMonths(1) : date.plusMonths(1));
    }

    public void getDate(Update update, LocalDate date) {
        String data = update.getCallbackQuery().getData();
        Message message = getMessage(update);
        EditMessageText editMessageText = new EditMessageText();
        if (data.endsWith(ONE_DAY)) {
            editMessageText.setText(GET_DATE_TEXT);
            editMessageText.setReplyMarkup(keyboardService.createDate(date, data.substring(EmployeeState.CHOOSE_BOOKING_TYPE.length())));
        } else if (data.length() < 35 && data.startsWith(DATE) && data.endsWith(CONTINUOUS)) {
            editMessageText.setText(GET_CONTINUOUS_DATE_END);
            editMessageText.setReplyMarkup(keyboardService.createDate(date, data.substring(DATE.length())));
        } else if (data.endsWith(CONTINUOUS)) {
            editMessageText.setText(GET_CONTINUOUS_DATE_BEGIN);
            editMessageText.setReplyMarkup(keyboardService.createDate(date, data.substring(EmployeeState.CHOOSE_BOOKING_TYPE.length())));
        }
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        telegramFeign.editMessageText(editMessageText);
    }

    public static Message getMessage(Update update) {
        return (update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage());
    }

    public void getSeats(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_SEAT);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getSeats(data.substring(DATE.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getDayOfWeeK(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_DAY_OF_WEEK);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getDayOfWeek(data.substring(EmployeeState.CHOOSE_RECURRING_TIME.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getDayOfWeekForUpdate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(":");
        String recurringTime = split[split.length - 1];
        String currBookingId = split[1];
        EditMessageText editMessageText = new EditMessageText(CHOOSE_DAY_OF_WEEK);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getDayOfWeek(" " + RECURRING + " " + currBookingId + recurringTime));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getRecurringTime(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_RECURRING_TIME);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getRecurringTime(EmployeeState.CHOOSE_RECURRING_TIME + data.substring(CHOOSE_BOOKING_TYPE.length(), data.length() - RECURRING.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getRecurringTimeForEdit(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_RECURRING_TIME);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getRecurringTime(data + ":"));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getSeatsByRecurring(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(CHOOSE_SEAT);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getSeatsByRecurring(data.substring(GET_DAY_OF_WEEK.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getParking(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText(HAS_PARKING);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        if (data.startsWith(GET_SEATS_RECURRING))
            editMessageText.setReplyMarkup(keyboardService.getHasParking(data.substring(GET_SEATS_RECURRING.length())));
        else if (data.startsWith(ONE_DAY))
            editMessageText.setReplyMarkup(keyboardService.getHasParking(data.substring(ONE_DAY.length())));
        else if (data.startsWith(CONTINUOUS))
            editMessageText.setReplyMarkup(keyboardService.getHasParking(data.substring(CONTINUOUS.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getReview(Update update) {
        Message message = getMessage(update);
        String data = update.getCallbackQuery().getData();
        List<String> review = keyboardService.getReview(data.substring(GET_PARKING.length()));
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setText(REVIEW + "\n" + review.get(0));
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getReviewInline(review.get(1) + data.substring(GET_PARKING.length())));
        telegramFeign.editMessageText(editMessageText);
    }

    public void bookWorkPlace(Update update) {
        final SendMessage booking = keyboardService.booking(update);
        telegramFeign.sendMessage(booking);
    }

    public void getPrevBooking(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(NEXT_BOOKING.length() + 1);
        List<BookingResponseDto> bookings = hotDeskFeign.findBookingsByEmId(getEmployeeId(message.getChatId().toString()));
        int index = 0;
        for (BookingResponseDto booking : bookings) {
            if (booking.getId().toString().equals(currBookingId)) {
                String prevId = bookings.get(index - 1).getId().toString();
                EditMessageText editMessageText = new EditMessageText(textBuilderForBooking(bookings.get(index - 1)));
                editMessageText.setMessageId(message.getMessageId());
                editMessageText.setChatId(message.getChatId().toString());
                if (index - 1 == 0) {
                    editMessageText.setReplyMarkup(keyboardService.myBookingInlineKeyboard(prevId, false, true));
                } else {
                    editMessageText.setReplyMarkup(keyboardService.myBookingInlineKeyboard(prevId, true, true));
                }
                telegramFeign.editMessageText(editMessageText);
                return;
            }
            index++;
        }
    }

    public void getNextBooking(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(NEXT_BOOKING.length() + 1);
        List<BookingResponseDto> bookings = hotDeskFeign.findBookingsByEmId(getEmployeeId(message.getChatId().toString()));
        int size = bookings.size();
        int order = 0;
        for (BookingResponseDto booking : bookings) {
            if (booking.getId().toString().equals(currBookingId)) {
                String nextId = bookings.get(order + 1).getId().toString();
                EditMessageText editMessageText = new EditMessageText(textBuilderForBooking(bookings.get(order + 1)));
                editMessageText.setMessageId(message.getMessageId());
                editMessageText.setChatId(message.getChatId().toString());
                if (order + 2 == size) {
                    editMessageText.setReplyMarkup(keyboardService.myBookingInlineKeyboard(nextId, true, false));
                } else {
                    editMessageText.setReplyMarkup(keyboardService.myBookingInlineKeyboard(nextId, true, true));
                }
                telegramFeign.editMessageText(editMessageText);
                return;
            }
            order++;
        }
    }

    public void cancelBooking(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(CANCEL.length() + 1);
        EditMessageText editMessageText = new EditMessageText("\n" + message.getText() + "\n Do you really want to cancel?");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.confirmationMenuButtons(currBookingId, false));
        telegramFeign.editMessageText(editMessageText);
    }

    public void editBooking(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(EDIT.length() + 1);
        EditMessageText editMessageText = new EditMessageText("\n\n" + message.getText() + "\n\n Choose your option editing > ");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getMainEditMenu(currBookingId));
        telegramFeign.editMessageText(editMessageText);
    }

    public void confirmedCancelling(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(CONFIRM_CANCELLING.length() + 1);
        BookingResponseDto bookingById = hotDeskFeign.getBookingById(Long.valueOf(currBookingId));
        List<LocalDate> dates = bookingById.getDates();
        if (dates != null) {
            LocalDate start = dates.get(0);
            BookingResponseDto bookingResponseDto = new BookingResponseDto();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            try {
                if (dates.size() == 1) {
                    bookingResponseDto = hotDeskFeign.cancelBooking(Long.parseLong(currBookingId), start, null);
                } else {
                    LocalDate end = dates.get(1);
                    bookingResponseDto = hotDeskFeign.cancelBooking(Long.parseLong(currBookingId), start, end);
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendMessage.setText(e.getMessage());
                telegramFeign.sendMessage(sendMessage);
                deleteMessage(update);
                return;
            }
            sendMessage.setText("Successfully cancelled");
            telegramFeign.sendMessage(sendMessage);
            deleteMessage(update);
        }
    }

    public void confirmedEditingForDate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = (data.indexOf(":") > 0) ? data.split(":") : data.split(" ");
        String currBookingId = split[1];
        List<LocalDate> newDates = new ArrayList<>();
        if (data.endsWith(CONTINUOUS)) {
            String startDate = split[2];
            String endDate = split[3];
            keyboardService.getContinuousDays(newDates, startDate, endDate);
        } else {
            for (int i = 2; i <= split.length - 1; i++) {
                newDates.add(LocalDate.parse(split[i]));
            }
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        deleteMessage(update);
        try {
            BookingResponseDto bookingById = hotDeskFeign.getBookingById(Long.valueOf(currBookingId));
            bookingById.setDates(newDates);
            BookingUpdateDto bookingUpdateDto = modelMapper.map(bookingById, BookingUpdateDto.class);
            hotDeskFeign.updateBooking(Long.valueOf(currBookingId), bookingUpdateDto);
            sendMessage.setText("Updated Successfully");
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
        }
        telegramFeign.sendMessage(sendMessage);
    }

    public void editBookingDate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String currBookingId = data.substring(NEW_DATE.length() + 1);
        EditMessageText editMessageText = new EditMessageText("Choose the type of booking dates");
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.createDateType("id:" + currBookingId + ":"));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getDateForEditOneDay(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(":");
        String currBookingId = split[1];
        EditMessageText editMessageText = new EditMessageText("Choose the new date: ");
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.createDate(LocalDate.now(), ":" + currBookingId + ":id"));
        telegramFeign.editMessageText(editMessageText);
    }

    public void choseNewDate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(":");
        String currBookingId = split[1];
        LocalDate newDate = LocalDate.parse(split[0].substring(DATE.length()));
        EditMessageText editMessageText = new EditMessageText("\n\n" + textBuilderForBooking(hotDeskFeign.getBookingById(Long.valueOf(currBookingId))) + "\n\nNew Date: " + newDate + "\n\n");
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.confirmationMenuButtons(":" + currBookingId + ":" + newDate, true));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getConfirmMenuForRecurringUpdate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(" ");
        Long bookingId = Long.valueOf(split[2]);
        int days = Integer.parseInt(split[3]);
        int dayOfWeek = keyboardService.checkDayOfWeek(split[split.length - 1]);
        LocalDate date = LocalDate.now();
        List<LocalDate> dates = new ArrayList<>();
        while (days > 0) {
            if (date.getDayOfWeek().getValue() == dayOfWeek) {
                days--;
                dates.add(date);
                date = date.plusWeeks(1);
                continue;
            }
            date = date.plusDays(1);
        }
        EditMessageText editMessageText = new EditMessageText("\n\nBooking Id: " + bookingId + "\n\nNew Date(s): " + dates + ";");
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        StringBuilder datas = new StringBuilder();
        datas.append(bookingId);
        for (LocalDate l : dates) {
            datas.append(" ").append(l);
        }
        editMessageText.setReplyMarkup(keyboardService.confirmationMenuButtons(datas.toString(), true));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getStartDateForEditContinuous(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(":");
        String bookingId = split[1];
        EditMessageText editMessageText = new EditMessageText("\n\nSelect the start date:\n\n");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.createDate(LocalDate.now(), ":" + bookingId + ":" + GET_CONTINUOUS_DATE_END));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getEndDateForUpdate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] split = data.split(":");
        String bookingId = split[1];
        String startDate = split[0].substring(DATE.length());
        EditMessageText editMessageText = new EditMessageText("\n\nSelect the end date:\n\n");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.createDate(LocalDate.now(), " " + startDate + " " + bookingId + " " + CHOSE_END_DATE_FOR_UPDATE));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getConfirmMenuForContinuousUpdate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String[] s = data.split(" ");
        String startDate = s[1];
        String endDate = s[0].substring(DATE.length());
        String bookingId = s[2];
        EditMessageText editMessageText = new EditMessageText("\n\nBooking Id: " + bookingId + "\n\nStart Date: " + startDate + "\n\nEnd Date: " + endDate);
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.confirmationMenuButtons(bookingId + " " + startDate + " " + endDate + " " + CONTINUOUS, true));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getOfficeForUpdate(Update update) {
        final String[] s = update.getCallbackQuery().getData().split(" ");
        final Message message = getMessage(update);
        String text = message.getText();
        String city = text.substring(text.indexOf("City:") + 5, text.indexOf("Street:")).trim();
        String bookingId = s[1];
        EditMessageText editMessageText = new EditMessageText("\n\n " + textBuilderForBooking(hotDeskFeign.getBookingById(Long.valueOf(bookingId))) + "\n\n Choose the new Office");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.officeMenuForUpdate(city, bookingId));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getSeatsForUpdate(Update update) {
        final String[] s = update.getCallbackQuery().getData().split("\\s+");
        final Message message = getMessage(update);
        final String text = message.getText();
        BookingResponseDto bookingById = hotDeskFeign.getBookingById(Long.valueOf(s[1]));
        String addressId = s[2];
        EditMessageText editMessageText = new EditMessageText("\n\n " + textBuilderForBooking(bookingById) + "\n\n Choose the new Seat");
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setReplyMarkup(keyboardService.getSeatsForUpdate(GET_NEW_SEAT_FOR_UPDATE, bookingById.getDates(), s[1], addressId));
        telegramFeign.editMessageText(editMessageText);
    }

    public void getConfirmMenuForOfficeUpdate(Update update) {
        final Message message = getMessage(update);
        final String data = update.getCallbackQuery().getData();
        String text = message.getText();
        text = text.replace("Choose the new Seat","");
        EditMessageText editMessageText = new EditMessageText(text+"\nPlease confirm.");
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(keyboardService.confirmationMenuButtons(data, true));
        telegramFeign.editMessageText(editMessageText);
    }

    public void confirmedEditingForOffice(Update update) {
        final Message message = getMessage(update);
        String[] split = update.getCallbackQuery().getData().split("\\s+");
        String bookingId = split[2];
        String officeId = split[3];
        String seatId = split[4];
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        try {
            BookingResponseDto bookingById = hotDeskFeign.getBookingById(Long.valueOf(bookingId));
            OfficeResponseDto office = hotDeskFeign.getOffice(Long.valueOf(officeId));
            bookingById.setOffice(office);
            bookingById.setSeatId(Long.valueOf(seatId));
            hotDeskFeign.updateBooking(Long.valueOf(bookingId), modelMapper.map(bookingById, BookingUpdateDto.class));
            deleteMessage(update);
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
        }
        sendMessage.setText("successfully updated");
        telegramFeign.sendMessage(sendMessage);
    }
}