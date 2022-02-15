package com.exadel.hotdesk.role.dto;

import com.exadel.hotdesk.permission.dto.PermissionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleResponseDto extends RoleBaseDto {
    private Long id;
    private List<PermissionResponseDto> perResponseDtoList;
    private LocalDateTime created;
    private LocalDateTime modified;
}