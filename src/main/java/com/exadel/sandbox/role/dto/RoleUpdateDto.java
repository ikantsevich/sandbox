package com.exadel.sandbox.role.dto;

import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleUpdateDto extends RoleBaseDto {
    private List<PermissionUpdateDto> permissionUpdateDtoList;
}
