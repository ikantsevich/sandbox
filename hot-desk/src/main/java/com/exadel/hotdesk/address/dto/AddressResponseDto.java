package com.exadel.hotdesk.address.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressResponseDto extends  AddressBaseDto{

    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;

}
