package com.exadel.sandbox.officeFloorAttachment.dto.floorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorResponseDto {
    private Long flId;
    private Long ofId;
    private Long atId;
    private int flNum;
    private String kitchenStatus;
    private String meetingRoomStatus;
}
