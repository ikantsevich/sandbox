package com.exadel.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date flCreated;
    private Date flModified;
}
