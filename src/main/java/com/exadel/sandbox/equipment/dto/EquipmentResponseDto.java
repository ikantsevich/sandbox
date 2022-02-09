package com.exadel.sandbox.equipment.dto;

import com.exadel.sandbox.seat.dto.SeatResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentResponseDto extends EquipmentBaseDto{
    private Long id;
    private SeatResponseDto seatResponseDto;
    private LocalDateTime created;
    private LocalDateTime modified;
}
