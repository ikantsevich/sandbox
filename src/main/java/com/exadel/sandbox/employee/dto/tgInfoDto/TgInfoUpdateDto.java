package com.exadel.sandbox.employee.dto.tgInfoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TgInfoUpdateDto extends TgInfoBaseDto {
    private String chatState;
}
