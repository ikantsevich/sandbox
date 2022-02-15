package com.exadel.sandbox.officeFloorAttachment.dto.floorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorCreateDto {
    private Long ofId;
    private Long atId;
    private int flNum;
}
