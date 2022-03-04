package com.exadel.sandbox.officeFloor.dto.floorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorBaseDto {
    @Min(value = 1L, message = "minimum value 1")
    private int floorNum;
    @NotNull(message = "cannot be null")
    private String kitchenStatus;
    @NotNull(message = "cannot be null")
    private String meetingRoomStatus;
}
