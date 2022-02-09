package com.exadel.sandbox.address.dto;

import lombok.Data;

@Data
public class AddressBaseDto {

    private String country;
    private String city;
    private String street;
    private int buildingNum;
    private String zipCode;

}
