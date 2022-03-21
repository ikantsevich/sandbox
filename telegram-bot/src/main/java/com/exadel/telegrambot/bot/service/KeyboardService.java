package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.address.dto.AddressBaseDto;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.exadel.telegrambot.bot.service.BotService.getMessage;
import static com.exadel.telegrambot.bot.utils.Constant.CHOSE_NEW_SEAT;
import static com.exadel.telegrambot.bot.utils.Constant.NEW_DATE;
import static com.exadel.telegrambot.bot.utils.Constant.*;
import static com.exadel.telegrambot.bot.utils.EmployeeState.*;

@Component
@RequiredArgsConstructor
public class KeyboardService {
    private final HotDeskFeign hotDeskFeign;

    private InlineKeyboardMarkup getInlineKeyboard(String callback, List<String> name, List<String> callbackData) {
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        for (int i = 0; i < name.size(); i++) {
            buttons.add(getButton(callback + " " + callbackData.get(i), name.get(i)));
            if (i % 2 == 1) {
                inlineKeyboard.add(buttons);
                buttons = new ArrayList<>();
            }

        }
        if (!buttons.isEmpty()) {
            inlineKeyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        final InlineKeyboardButton button = getButton(DELETE, DELETE);
        buttons.add(button);
        inlineKeyboard.add(buttons);
        return new InlineKeyboardMarkup(inlineKeyboard);
    }

    private InlineKeyboardMarkup getInlineKeyboard(List<List<String>> name, List<List<String>> callbackData, List<List<String>> urls) {
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

    private ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons) {
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

    private ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
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

    private ReplyKeyboardMarkup getReplyKeyboard(List<List<String>> buttons,
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

    public ReplyKeyboardMarkup homeMenu() {
        return getReplyKeyboard(List.of(List.of(MY_BOOKINGS, NEW_BOOKING)));
    }

    public InlineKeyboardMarkup countryMenu(Long employeeId) {
        try {
            List<String> countries = hotDeskFeign.getAddresses().stream().map(AddressBaseDto::getCountry).distinct().collect(Collectors.toList());
            return getInlineKeyboard(COUNTRIES, countries, countries);
        } catch (FeignException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InlineKeyboardMarkup cityMenu(String country) {
        try {
            final List<String> cities = hotDeskFeign.getAddresses().stream().map(address -> {
                if (address.getCountry().equals(country))
                    return address.getCity();
                return null;
            }).distinct().filter(Objects::nonNull).collect(Collectors.toList());

            return getInlineKeyboard(CITIES, cities, cities);
        } catch (FeignException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InlineKeyboardMarkup confirmationMenuButtons(String bookingId, boolean isEdit) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        if (isEdit)
            rows.add(getButton(CONFIRM_EDITING_FOR_DATE + " " + bookingId, "✅"));
        else
            rows.add(getButton(CONFIRM_CANCELLING + " " + bookingId, "✅"));
        rows.add(getButton(BACK_TO_BOOKINGS + " " + bookingId, "🔙"));
        inlineKeyboardButtons.add(rows);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup myBookingInlineKeyboard(String bookingId, boolean hasPrev, boolean hasNext) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        if (hasPrev)
            rows.add(getButton(PREV_BOOKING + " " + bookingId, "⏮️"));
        rows.add(getButton(CANCEL + " " + bookingId, "❌"));
        rows.add(getButton(EDIT + " " + bookingId, "EDIT"));
        if (hasNext)
            rows.add(getButton(NEXT_BOOKING + " " + bookingId, "⏭️"));
        inlineKeyboardButtons.add(rows);
        rows = new ArrayList<>();
        rows.add(getButton(BACK_TO_MAIN_MENU, "🔙"));
        inlineKeyboardButtons.add(rows);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup officeMenu(String city) {
        try {
            List<String> office = hotDeskFeign.getAddresses().stream().map(address -> {
                if (address.getCity().equals(city))
                    return (address.getStreet() + " " + address.getBuildingNum());
                return null;
            }).distinct().filter(Objects::nonNull).collect(Collectors.toList());

            List<String> addressId = hotDeskFeign.getAddresses().stream().map(address -> {
                if (address.getCity().equals(city))
                    return address.getId().toString();
                return null;
            }).distinct().filter(Objects::nonNull).collect(Collectors.toList());

            return getInlineKeyboard(OFFICE, office, addressId);
        } catch (FeignException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InlineKeyboardMarkup officeMenuForUpdate(String city, String bookingId) {
        try {
            List<String> office = hotDeskFeign.getAddresses().stream().map(address -> {
                if (address.getCity().equals(city))
                    return (address.getStreet() + " " + address.getBuildingNum());
                return null;
            }).distinct().filter(Objects::nonNull).collect(Collectors.toList());

            List<String> addressId = hotDeskFeign.getAddresses().stream().map(address -> {
                if (address.getCity().equals(city))
                    return address.getId().toString();
                return null;
            }).distinct().filter(Objects::nonNull).collect(Collectors.toList());

            return getInlineKeyboard(OFFICE_FOR_UPDATE + " " + bookingId, office, addressId);
        } catch (FeignException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InlineKeyboardMarkup createDateType(String callback) {
        List<String> dateType = new ArrayList<>(List.of(ONE_DAY, CONTINUOUS, RECURRING));
        return getInlineKeyboard(callback, dateType, dateType);
    }


    public InlineKeyboardMarkup createDate(LocalDate date, String data) {
        String dateStr = date.toString();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(Collections.singletonList(getButton(SKIP, date.getMonth().name() + " " + date.getYear())));
        List<InlineKeyboardButton> weeks = new ArrayList<>();
        for (String week : Arrays.asList("M", "T", "W", "R", "F", "S", "U"))
            weeks.add(getButton(SKIP, week));
        rowList.add(weeks);

        int monthValue = date.getMonthValue();
        date = date.minusDays(date.getDayOfMonth() - 1);
        Map<String, String> days = new LinkedHashMap<>();
        while (date.getMonthValue() == monthValue) {
            for (int i = 1; i <= 7; i++) {
                if (i == date.getDayOfWeek().getValue() && date.getMonthValue() == monthValue) {
                    days.put(DATE + date + data, String.valueOf(date.getDayOfMonth()));
                    date = date.plusDays(1);
                } else
                    days.put(SKIP + i, " ");
            }
            rowList.add(getRow(days));
            days = new LinkedHashMap<>();
        }
        LinkedHashMap<String, String> floor = new LinkedHashMap<>();
        floor.put(PREV + dateStr, PREV);
        floor.put(BACK_TO_GET_TO_OFFICE, BACK);
        floor.put(NEXT + dateStr, NEXT);
        rowList.add(getRow(floor));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    public InlineKeyboardMarkup getSeats(String data) {
        List<LocalDate> localDates = new ArrayList<>();
        String offId = null;
        boolean isContinuous = false;
        if (data.endsWith(CONTINUOUS)) {
            isContinuous = true;
            String end = data.substring(0, 10);
            String begin = data.substring(10, 20);
            getContinuousDays(localDates, begin, end);
            offId = getOfficeId(data, true);
        } else if (data.endsWith(ONE_DAY)) {
            String date = data.substring(0, 10);
            localDates.add(LocalDate.parse(date));
            offId = getOfficeId(data, false);
        }
        assert offId != null;
        final OfficeResponseDto officeByAddressId = getOfficeByAddressId(Long.valueOf(offId));
        final List<SeatResponseDto> seatsByOfficeIdAndDate = getListOfSeats(officeByAddressId.getId(), localDates);
        return getInlineKeyboard((isContinuous ? CONTINUOUS : ONE_DAY) + localDates + offId, getTextOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()), getCallbackOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()));
    }


    public InlineKeyboardMarkup getSeatsForUpdate(String getNewSeatForUpdate, List<LocalDate> dates, String bookingId, String addressId) {
        OfficeResponseDto officeByAddressId = hotDeskFeign.getOfficeByAddressId(Long.valueOf(addressId));
        List<SeatResponseDto> seatsByOfficeIdAndDate = hotDeskFeign.getSeatsByOfficeIdAndDate(officeByAddressId.getId(), dates);
        return getInlineKeyboard(CHOSE_NEW_SEAT + " " + bookingId + " " + addressId, getTextOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()), getCallbackOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()));
    }

    public void getContinuousDays(List<LocalDate> localDates, String begin, String end) {
        LocalDate beginning = LocalDate.parse(begin);
        LocalDate ending = LocalDate.parse(end);
        while (!String.valueOf(beginning).equals(String.valueOf(ending))) {
            localDates.add(beginning);
            beginning = beginning.plusDays(1);
        }
        localDates.add(ending);
    }

    private OfficeResponseDto getOfficeByAddressId(Long id) {
        return hotDeskFeign.getOfficeByAddressId(id);
    }

    private List<SeatResponseDto> getListOfSeats(Long id, List<LocalDate> localDates) {
        return hotDeskFeign.getSeatsByOfficeIdAndDate(id, localDates);
    }


    public List<String> getCallbackOfSeats(List<SeatResponseDto> seatsByOfficeIdAndDate, List<String> callback) {
        for (SeatResponseDto seatResponseDto : seatsByOfficeIdAndDate) {
            callback.add(String.valueOf(seatResponseDto.getId()));
        }
        return callback;
    }

    public List<String> getTextOfSeats(List<SeatResponseDto> seatsByOfficeIdAndDate, List<String> text) {
        for (SeatResponseDto seatResponseDto : seatsByOfficeIdAndDate) {
            StringBuilder stringBuilderText = new StringBuilder();
            stringBuilderText.append(seatResponseDto.getNumber())
                    .append("  ")
                    .append(seatResponseDto.getFloorNum())
                    .append("  ")
                    .append(seatResponseDto.getStatus());
            text.add(stringBuilderText.toString());
        }
        return text;
    }


    public String getOfficeId(String data, boolean isContinuous) {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        if (isContinuous)
            i = 20;
        else
            i = 10;
        for (; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i)))
                stringBuilder.append(data.charAt(i));
            else if (data.charAt(i) == ' ')
                break;
        }
        return stringBuilder.toString();
    }

    protected InlineKeyboardButton getButton(String callBackData, String text) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callBackData);
        return button;
    }

    protected List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return new ArrayList<>(Arrays.asList(buttons));
    }

    protected List<InlineKeyboardButton> getRow(Map<String, String> buttons) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String data : buttons.keySet())
            row.add(getButton(data, buttons.get(data)));
        return row;
    }

    public InlineKeyboardMarkup getDayOfWeek(String data) {
        List<String> dayList = new ArrayList<>(List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY));
        return getInlineKeyboard(GET_DAY_OF_WEEK + data, dayList, dayList);
    }

    public InlineKeyboardMarkup getRecurringTime(String data) {
        return getNumbers(data);
    }

    private InlineKeyboardMarkup getNumbers(String data) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            buttons.add(getButton(data + " " + i, String.valueOf(i)));
            if (i % 5 == 0) {
                rows.add(buttons);
                buttons = new ArrayList<>();
            }
        }
        if (!buttons.isEmpty())
            rows.add(buttons);

        return new InlineKeyboardMarkup(rows);
    }

    public InlineKeyboardMarkup getSeatsByRecurring(String data) {
        final String addressId = data.substring(0, data.indexOf(' '));
        StringBuilder recurring = new StringBuilder();
        StringBuilder dayOfWeek = new StringBuilder();
        for (int i = data.indexOf(' ') + 1; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i)))
                recurring.append(data.charAt(i));
            else if (Character.isAlphabetic(data.charAt(i)))
                dayOfWeek.append(data.charAt(i));
        }
        int days = Integer.parseInt(String.valueOf(recurring));
        LocalDate date = LocalDate.now();
        final int day = checkDayOfWeek(dayOfWeek.toString());
        List<LocalDate> dates = new ArrayList<>();
        while (days > 0) {
            if (date.getDayOfWeek().getValue() == day) {
                days--;
                dates.add(date);
                date = date.plusWeeks(1);
                continue;
            }
            date = date.plusDays(1);
        }
        final OfficeResponseDto officeByAddressId = getOfficeByAddressId(Long.valueOf(addressId));
        final List<SeatResponseDto> seatsByOfficeIdAndDate = getListOfSeats(officeByAddressId.getId(), dates);
        return getInlineKeyboard(GET_SEATS_RECURRING + dates + addressId, getTextOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()), getCallbackOfSeats(seatsByOfficeIdAndDate, new ArrayList<>()));
    }


    public int checkDayOfWeek(String dayOfWeek) {
        int i = 1;
        for (String day : List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY)) {
            if (dayOfWeek.equals(day))
                return i;
            i++;
        }
        return 0;
    }

    public InlineKeyboardMarkup getHasParking(String data) {
        List<String> hasParking = new ArrayList<>(List.of(YES, NO));
        return getInlineKeyboard(GET_PARKING + data, hasParking, hasParking);
    }

    public List<String> getReview(String data) {
        final List<LocalDate> dates = getDates(data.substring(data.indexOf("[") + 1, data.indexOf("]")));
        StringBuilder stringBuilder = new StringBuilder();
        OfficeResponseDto officeByAddressId = getOfficeByAddressId(getOfficeId(data));
        Long seatId;
        if (data.endsWith(YES)) {
            seatId = getSeatId(data.substring(0, data.length() - YES.length()));
        } else {
            seatId = getSeatId(data.substring(0, data.length() - NO.length()));
        }
        SeatResponseDto seatById = hotDeskFeign.getSeatById(seatId);
        stringBuilder.append(officeByAddressId.getAddress().getCountry())
                .append("  ")
                .append(officeByAddressId.getAddress().getCity())
                .append("  ")
                .append(officeByAddressId.getAddress().getStreet())
                .append("  ")
                .append(officeByAddressId.getAddress().getBuildingNum())
                .append("\n")
                .append(officeByAddressId.getOfficeStatus())
                .append("  ")
                .append("\n")
                .append(seatById.getFloorNum())
                .append("  ")
                .append(seatById.getNumber())
                .append("  ")
                .append(seatById.getStatus());
        List<ParkingSpotResponseDto> freeParkingSpots = hotDeskFeign.getFreeParkingSpots(officeByAddressId.getId(), dates);
        if (data.endsWith(YES)) {
            stringBuilder
                    .append("  ")
                    .append("\n")
                    .append(freeParkingSpots.get(0).getSpotNum());
        }
        List<String> list = new ArrayList<>();
        list.add(stringBuilder.toString());
        list.add(String.valueOf(freeParkingSpots.get(0).getId()));
        return list;
    }

    private Long getSeatId(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (int i = data.length() - 1; data.charAt(i) != ']'; i--) {
            if (Character.isDigit(data.charAt(i)))
                stringBuilder.append(data.charAt(i));
            if (data.charAt(i) == ' ')
                count++;
            if (count == 2)
                return Long.valueOf(stringBuilder.toString());
        }
        return Long.valueOf(stringBuilder.toString());
    }

    public InlineKeyboardMarkup getReviewInline(String data) {
        List<String> list = new ArrayList<>(List.of(CONFIRM, CANCEL));
        if (data.contains(YES)) {
            return getInlineKeyboard(GET_REVIEW + data.substring(0, data.length() - YES.length()), list, list);
        }
        return getInlineKeyboard(GET_REVIEW + data.substring(0, data.length() - YES.length()), list, list);
    }

    private Long getOfficeId(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = data.indexOf("]") + 1; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i)))
                stringBuilder.append(data.charAt(i));
            else if (data.charAt(i) == ' ')
                break;
        }
        return Long.parseLong(stringBuilder.toString());
    }

    private List<LocalDate> getDates(String dates) {
        List<LocalDate> localDates = new ArrayList<>();
        final String[] split = dates.split(", ");
        for (String s : split) {
            localDates.add(LocalDate.parse(s));
        }
        return localDates;
    }

    public SendMessage booking(Update update) {
        final String data = update.getCallbackQuery().getData();
        final Message message = getMessage(update);
        if (data.endsWith(CONFIRM)) {
            try {
                final Long seatId = getSeatId(data.substring(0, data.length() - CONFIRM.length() - 1));
                final List<LocalDate> dates = getDates(data.substring(data.indexOf("[") + 1, data.indexOf("]")));
                final EmployeeResponseDto employeeByChatId = hotDeskFeign.getEmployeeByChatId(message.getChatId().toString());
                final Long id = employeeByChatId.getId();
                Long parkingId1 = getParkingId(data.substring(GET_REVIEW.length()));
                final Long parkingId = parkingId1 == 0 ? null : parkingId1;
                BookingCreateDto bookingCreateDto = new BookingCreateDto();
                bookingCreateDto.setDates(dates);
                bookingCreateDto.setParkingSpotId(parkingId);
                bookingCreateDto.setEmployeeId(id);
                bookingCreateDto.setSeatId(seatId);
                hotDeskFeign.createBooking(bookingCreateDto);
                return new SendMessage(message.getChatId().toString(), "Booking added successfully");
            } catch (Exception e) {
                return new SendMessage(message.getChatId().toString(), "You have already booked place for today");
            }
        }
        return new SendMessage();
    }

    private Long getParkingId(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; data.charAt(i) != '['; i++) {
            if (Character.isDigit(data.charAt(i)))
                stringBuilder.append(data.charAt(i));
        }
        try {
            return Long.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    public InlineKeyboardMarkup getMainEditMenu(String bookingId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        rows.add(getButton(NEW_OFFICE + " " + bookingId, "Change Office"));
        rows.add(getButton(NEW_DATE + " " + bookingId, "New Date"));
        inlineKeyboardButtons.add(rows);
        rows = new ArrayList<>();
        rows.add(getButton(BACK_TO_BOOKINGS, "🔙"));
        inlineKeyboardButtons.add(rows);
        return inlineKeyboardMarkup;
    }
}
