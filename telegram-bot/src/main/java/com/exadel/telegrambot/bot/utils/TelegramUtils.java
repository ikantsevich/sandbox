package com.exadel.telegrambot.bot.utils;

public interface TelegramUtils {
    String TOKEN = "bot" + Security.TOKEN + "/";
    String TELEGRAM_BASE = "https://api.telegram.org/";

    String BASE_WEBHOOK = "api/telegram";
    String FULL_REQUEST = TELEGRAM_BASE + TOKEN;

    String GLOBAL = "https://e5d3-178-218-201-223.ngrok.io/";

    String HOT_DESK_URL = "http://localhost:8081/sandbox";
}