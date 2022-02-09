package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;
import com.exadel.sandbox.mapper.Mapper;

public class RoleMapperImpl implements Mapper<Role, RoleDto> {

    @Override
    public Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setRole_id(dto.getId());
        role.setName(dto.getName());
        role.setCreatedDate(dto.getCreatedDate());
        role.setModifiedDate(dto.getModifiedDate());
        return role;
    }

    @Override
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getRole_id());
        roleDto.setName(role.getName());
        roleDto.setCreatedDate(role.getCreatedDate());
        roleDto.setModifiedDate(role.getModifiedDate());
        return roleDto;
    }
}
