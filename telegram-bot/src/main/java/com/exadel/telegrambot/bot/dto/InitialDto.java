package com.exadel.telegrambot.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitialDto {
    private boolean ok;
    private boolean result;
    private String description;
}
