package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.PermissionDto;
import com.exadel.sandbox.entities.Permission;
import com.exadel.sandbox.mapper.Mapper;
import com.exadel.sandbox.mapper.impl.PermissionMapperImpl;
import com.exadel.sandbox.repositories.PermissionRepository;
import com.exadel.sandbox.service.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PermissionServiceImpl implements Service<PermissionDto> {
    private final PermissionRepository permissionRepository;
    private final Mapper<Permission,PermissionDto> permissionMapper;

    @Override
    public List<PermissionDto> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PermissionDto getById(Integer id) {
        Permission permission = permissionRepository.getOne(id);
        if (permission == null) throw new RuntimeException("Permission not found");
        else return permissionMapper.toDto(permission);
    }

    @Override
    public PermissionDto create(PermissionDto permissionDto) {
        return permissionMapper.toDto(permissionRepository.save(permissionMapper.toEntity(permissionDto)));
    }

    @Override
    public void deleteById(Integer id) {
        getById(id);//throws exception if there is no permission with curr id
        permissionRepository.deleteById(id);
    }

    @Override
    public PermissionDto updateById(Integer id, PermissionDto permissionDto) {
        getById(id);
        Permission permission = permissionMapper.toEntity(permissionDto);
        permission.setPer_id(id);
        permissionRepository.save(permission);
        return permissionMapper.toDto(permission);
    }
}
