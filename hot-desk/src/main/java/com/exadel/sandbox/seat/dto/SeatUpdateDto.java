package com.exadel.sandbox.seat.dto;

import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SeatUpdateDto extends SeatBaseDto{
    private List<EquipmentUpdateDto> equipmentUpdateDtoList;
}
