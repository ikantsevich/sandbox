package com.exadel.telegrambot.bot.utils;

public interface EmployeeState {
    String START = "/start";
    String COUNTRIES = "countries";
    String CITIES = "cities";
    String OFFICE = "office";
    String SKIP_ACTION = "skip_action";
    String SEND_WARNING = "send_warning";
    String MAIN_MENU_SEND = "main_menu_send";
    String MAIN_MENU_EDIT = "main_menu_edit";
    String GET_DATE = "get_date";
    String CHOOSE_BOOKING_TYPE = "choose_booking_type";
    String GET_SEATS = "get_seats";
    String GET_SEATS_RECURRING = "get_seats_recurring";
    String GET_DAY_OF_WEEK = "get_day_of_the_week";
    String CHOOSE_RECURRING_TIME = "choose_recurring_time";
    String GET_PARKING = "get_parking";
    String GET_CONTINUOUS_DATE = "get_continuous_date";
    String GET_REVIEW = "get_review";
    String BOOKING = "booking";
    String NEXT_BOOKING = "next_booking:";
    String PREV_BOOKING = "prev_booking:";
    String DELETE_BOOKING="delete_booking";
    String EDIT_BOOKING="edit_booking";
}
