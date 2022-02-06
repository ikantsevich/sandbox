package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;

public class RoleMapperImpl implements RoleMapper{
    @Override
    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setCreatedDate(roleDto.getCreatedDate());
        role.setModifiedDate(roleDto.getModifiedDate());
        return role;
    }

    @Override
    public RoleDto toDto(Role role) {
        RoleDto roleDto=new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setCreatedDate(role.getCreatedDate());
        roleDto.setModifiedDate(role.getModifiedDate());
        return roleDto;
    }
}
