package com.exadel.hotdesk.parking_spot.dto;

import lombok.Data;

@Data
public class ParkingSpotBaseDto {

    private String status;
    private Integer spotNum;
    private Boolean hasECharger;

}
