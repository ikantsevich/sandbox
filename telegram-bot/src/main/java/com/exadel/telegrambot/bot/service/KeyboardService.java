package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.address.dto.AddressBaseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.utils.Constant;
import feign.FeignException;
import liquibase.pro.packaged.I;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
            buttons.add(getButton(callback + callbackData.get(i), name.get(i)));
            if (i % 2 == 0) {
                inlineKeyboard.add(buttons);
                buttons = new ArrayList<>();
            }

        }
        if (!buttons.isEmpty()) {
            inlineKeyboard.add(buttons);
        }
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
                    days.put(DATE + date + data.substring(Constant.CHOOSE_BOOKING_TYPE.length()), String.valueOf(date.getDayOfMonth()));
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
        String date = data.substring(0, 10);
        List<LocalDate> localDates = new ArrayList<>(List.of(LocalDate.parse(date)));
        String offId = getOfficeId(data);
        final OfficeResponseDto officeByAddressId = hotDeskFeign.getOfficeByAddressId(Long.valueOf(offId));
        final List<SeatResponseDto> seatsByOfficeIdAndDate = hotDeskFeign.getSeatsByOfficeIdAndDate(officeByAddressId.getId(), localDates);
        List<String> callback = new ArrayList<>();
        List<String> text = new ArrayList<>();
        for (SeatResponseDto seatResponseDto : seatsByOfficeIdAndDate) {
            StringBuilder stringBuilderCallback = new StringBuilder();
            StringBuilder stringBuilderText = new StringBuilder();
            stringBuilderCallback.append(seatResponseDto.getId());
            stringBuilderText.append(seatResponseDto.getNumber())
                    .append("  ")
                    .append(seatResponseDto.getFloorNum())
                    .append("  ")
                    .append(seatResponseDto.getStatus())
                    .append("  \uD83D\uDCBA");
            callback.add(stringBuilderCallback.toString());
            text.add(stringBuilderText.toString());
        }
        return getInlineKeyboard(date + offId, text, callback);
    }

    public String getOfficeId(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 10; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i)))
                stringBuilder.append(data.charAt(i));
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

    public InlineKeyboardMarkup getDayOfWeek(String data){
        List<String> dayList = new ArrayList<>(List.of("M", "T", "W", "R", "F", "S", "U"));
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
}
