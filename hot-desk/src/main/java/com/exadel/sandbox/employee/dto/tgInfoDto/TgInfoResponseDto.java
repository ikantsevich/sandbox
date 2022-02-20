package com.exadel.sandbox.employee.dto.tgInfoDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id",
        "chat-id",})
public class TgInfoResponseDto extends TgInfoBaseDto {
    private Long id;
    @JsonProperty("chat-state")
    private String chatState;
    private LocalDateTime created;
    private LocalDateTime modified;
}
