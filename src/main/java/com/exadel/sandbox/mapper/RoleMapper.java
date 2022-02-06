package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;

public interface RoleMapper {
    Role toEntity(RoleDto roleDto);
    RoleDto toDto(Role role);
}
