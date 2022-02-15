package com.exadel.hotdesk.employee.dto.tgInfoDto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TgInfoResponseDto extends TgInfoBaseDto {
    private Long id;
    private String chatState;
    private LocalDateTime created;
    private LocalDateTime modified;
}
