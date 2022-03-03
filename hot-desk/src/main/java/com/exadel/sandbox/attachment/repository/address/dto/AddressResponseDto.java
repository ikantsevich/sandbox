package com.exadel.sandbox.attachment.repository.address.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({
        "id"
})
public class AddressResponseDto extends AddressBaseDto {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;

}
