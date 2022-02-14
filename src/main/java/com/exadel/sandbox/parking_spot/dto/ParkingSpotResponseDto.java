package com.exadel.sandbox.parking_spot.dto;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.parking.dto.ParkingResponseDto;
import com.exadel.sandbox.parking.entity.Parking;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingSpotResponseDto extends ParkingSpotBaseDto {

    private Long id;
    private ParkingResponseDto parkingResponseDto;
    private LocalDateTime created;
    private LocalDateTime modified;

}
