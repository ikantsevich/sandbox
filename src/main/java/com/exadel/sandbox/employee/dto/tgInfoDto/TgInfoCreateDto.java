package com.exadel.sandbox.employee.dto.tgInfoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TgInfoCreateDto extends TgInfoBaseDto{
    private String chatState;
}
