package com.exadel.sandbox.officeFloor.dto.floorDto;

import com.exadel.sandbox.attachment.dto.AttachmentResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id"})
public class FloorResponseDto extends FloorBaseDto {
    private Long id;
    private Long officeId;
    private Long attachmentId;
}
