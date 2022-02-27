package dtos.parkingSpot.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"officeId", "hasECharger", "spotNum"})
public class ParkingSpotCreateDto extends ParkingSpotBaseDto {
    private Long officeId;
}
