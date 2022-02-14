package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SeatResponseDto extends SeatBaseDto{
    private Long id;
    private Long floorId;
    private LocalDateTime created;
    private LocalDateTime modified;
}
