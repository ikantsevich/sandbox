package com.exadel.sandbox.employee.dto.tgInfoDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TgInfoCreateDto extends TgInfoBaseDto {
    @JsonProperty("chat-state")
    private String chatState;
}
