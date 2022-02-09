package com.exadel.sandbox.role.dto;

import com.exadel.sandbox.permission.dto.PerBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleBaseDto {
    protected String name;
    private List<PerBaseDto> perBaseDtos;
}
