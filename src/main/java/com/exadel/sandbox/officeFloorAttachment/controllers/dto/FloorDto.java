package com.exadel.sandbox.officeFloorAttachment.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDto {
    private Long flId;
    private Long ofId;
    private Long atId;
    private int flNum;
    private String kitchenStatus;
    private String meetingRoomStatus;
    private LocalDateTime flCreated;
    private LocalDateTime flModified;
}
