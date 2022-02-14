package com.exadel.sandbox.parking_spot.dto;

import com.exadel.sandbox.address.entity.Address;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ParkingSpotBaseDto {

    private String status;
    private Integer spotNum;
    private Boolean hasECharger;

}
