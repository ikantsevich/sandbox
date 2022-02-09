package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.PermissionDto;
import com.exadel.sandbox.entities.Permission;
import com.exadel.sandbox.mapper.Mapper;

public class PermissionMapperImpl implements Mapper<Permission, PermissionDto> {
    @Override
    public Permission toEntity(PermissionDto dto) {
        Permission permission = new Permission();
        permission.setPer_id(dto.getId());
        permission.setName(dto.getName());
        permission.setCreatedDate(dto.getCreatedDate());
        permission.setModifiedDate(dto.getModifiedDate());
        return permission;
    }

    @Override
    public PermissionDto toDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getPer_id());
        permissionDto.setName(permission.getName());
        permissionDto.setCreatedDate(permission.getCreatedDate());
        permissionDto.setModifiedDate(permission.getModifiedDate());
        return permissionDto;
    }
}
