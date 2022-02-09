package com.exadel.sandbox.role.dto;

import com.exadel.sandbox.permission.dto.PerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleResponseDto extends RoleBaseDto {
    private Integer id;
    private List<PerResponseDto> perResponseDtoList;
    private Date created;
    private Date modified;
}
