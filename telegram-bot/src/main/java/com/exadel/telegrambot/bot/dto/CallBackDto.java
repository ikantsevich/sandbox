package com.exadel.telegrambot.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CallBackDto {
    private Long addId;
    private Long offId;
    private Long seatId;
    private List<LocalDate> dates;
    private Long parkingId;
    private String country;
}
