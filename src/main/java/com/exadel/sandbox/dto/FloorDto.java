package com.exadel.sandbox.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
    private LocalDateTime flCreated;
    private LocalDateTime flModified;
}
