package com.exadel.sandbox.employee.dto.tgInfoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TgInfoBaseDto{
    private String chatId;
    private String username;

}
