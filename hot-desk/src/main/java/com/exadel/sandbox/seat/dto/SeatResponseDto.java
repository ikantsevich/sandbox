package com.exadel.sandbox.seat.dto;

import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id"})
public class SeatResponseDto extends SeatBaseDto {
    private Long id;
    private Integer floorNum;
    private List<EquipmentResponseDto> equipmentResponseDtos;
    private LocalDateTime created;
    private LocalDateTime modified;
}
