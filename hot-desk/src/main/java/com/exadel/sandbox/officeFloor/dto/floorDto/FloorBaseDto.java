package com.exadel.sandbox.officeFloor.dto.floorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorBaseDto {
    private int floorNum;
    private String kitchenStatus;
    private String meetingRoomStatus;
}
