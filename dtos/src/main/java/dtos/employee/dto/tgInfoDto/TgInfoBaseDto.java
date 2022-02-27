package dtos.employee.dto.tgInfoDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TgInfoBaseDto {
    private String chatId;
    private String username;
}
