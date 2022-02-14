package com.exadel.sandbox.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMeDto {
    private boolean id;
    private User result;
}
