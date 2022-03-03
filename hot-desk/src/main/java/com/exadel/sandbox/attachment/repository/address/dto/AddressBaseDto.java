package com.exadel.sandbox.attachment.repository.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBaseDto {

    private String country;
    private String city;
    private String street;
    private Integer buildingNum;
    private String zipCode;

}
