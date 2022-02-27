package dtos.parkingSpot.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id",
        "spotNum",
        "status",
        "hasCharger",
        "officeResponseDto"})
public class ParkingSpotResponseDto extends ParkingSpotBaseDto {
    private Long id;
    private Long officeId;
    private LocalDateTime created;
    private LocalDateTime modified;
}
