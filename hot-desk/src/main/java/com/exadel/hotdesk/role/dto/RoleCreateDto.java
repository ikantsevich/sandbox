package com.exadel.hotdesk.role.dto;

import com.exadel.hotdesk.permission.dto.PermissionBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleCreateDto extends RoleBaseDto {
    private List<PermissionBaseDto> permissionList;
}