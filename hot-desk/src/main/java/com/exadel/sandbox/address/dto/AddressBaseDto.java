package com.exadel.sandbox.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBaseDto {
    @NotEmpty(message = "cannot be empty")
    private String country;
    @NotEmpty(message = "cannot be empty")
    private String city;
    @NotEmpty(message = "cannot be empty")
    private String street;
    @Min(value = 1L, message = "value must be at least 1")
    private Integer buildingNum;
    @Min(value = 1L, message = "value must be at least 1")
    private String zipCode;

}
