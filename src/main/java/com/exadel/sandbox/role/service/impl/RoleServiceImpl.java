package com.exadel.sandbox.role.service.impl;

import com.exadel.sandbox.permission.dto.PermissionBaseDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.role.dto.RoleCreateDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.dto.RoleUpdateDto;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.role.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements CrudService<RoleCreateDto, RoleUpdateDto, RoleResponseDto> {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public List<RoleResponseDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
        for (Role role : roles) {
            roleResponseDtos.add(entityToResponseDto(role));
        }
        return roleResponseDtos;
    }

    @Override
    public RoleResponseDto getById(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        return entityToResponseDto(byId.orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Override
    public RoleResponseDto create(RoleCreateDto roleCreateDto) {
        Role role = mapper.map(roleCreateDto, Role.class);
        List<Permission> permissions = new ArrayList<>();
        if (roleCreateDto.getPermissionCreateDtoList() != null) {
            for (PermissionBaseDto perBaseDto : roleCreateDto.getPermissionCreateDtoList()) {
                permissions.add(mapper.map(perBaseDto, Permission.class));
            }
            role.setPermissions(permissions);
        }
        return entityToResponseDto(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDto update(Long id, RoleUpdateDto roleUpdateDto) {
        Optional<Role> byId = roleRepository.findById(id);
        Role role = byId.orElseThrow(() -> new RuntimeException("Role not found"));
        List<Permission> permissions = new ArrayList<>();
        role.setId(id);
        role.setName(roleUpdateDto.getName());
        for (PermissionUpdateDto permissionUpdateDto : roleUpdateDto.getPermissionUpdateDtoList()) {
            permissions.add(mapper.map(permissionUpdateDto, Permission.class));
        }
        role.setPermissions(permissions);
        return entityToResponseDto(role);
    }

    private RoleResponseDto entityToResponseDto(Role role) {
        RoleResponseDto roleResponseDto = mapper.map(role, RoleResponseDto.class);
        if (role.getPermissions() != null) {
            List<PermissionResponseDto> perResponseDtos = new ArrayList<>();
            for (Permission permission : role.getPermissions()) {
                perResponseDtos.add(mapper.map(permission, PermissionResponseDto.class));
            }
            roleResponseDto.setPerResponseDtoList(perResponseDtos);
        }
        return roleResponseDto;
    }


}
