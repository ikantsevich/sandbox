package com.exadel.sandbox.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PerResponseDto extends PerBaseDto {
    private Integer id;
    private Date created;
    private Date modified;
}
