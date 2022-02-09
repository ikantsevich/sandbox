package com.exadel.sandbox.employee.dto.tgInfoDto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TgInfoResponseDto extends TgInfoBaseDto{
    private Long id;
    private String chatState;
    private LocalDateTime created;
    private LocalDateTime modified;
}
