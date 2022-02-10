package com.exadel.sandbox.role.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.permission.dto.PermissionBaseDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.permission.repository.PermissionRepository;
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
    private final PermissionRepository permissionRepository;
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
        return entityToResponseDto(byId.orElseThrow(() -> new EntityNotFoundException("Role not found")));
    }

    @Override
    public RoleResponseDto create(RoleCreateDto roleCreateDto) {
        Role role = mapper.map(roleCreateDto, Role.class);
        List<Permission> permissions = new ArrayList<>();
        for (PermissionBaseDto permissionBaseDto : roleCreateDto.getPermissionList()) {
            Permission permission = permissionRepository.getByName(permissionBaseDto.getName());
            if (permission == null) throw new EntityNotFoundException("Permission not found");
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        Role savedRole = roleRepository.save(role);
        return entityToResponseDto(savedRole);
    }

    @Override
    public void deleteById(Long id) {
        Role role = roleRepository.getOne(id);
        if (role != null) {
            role.setPermissions(null);
            roleRepository.save(role);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDto update(Long id, RoleUpdateDto roleUpdateDto) {
        Role role = roleRepository.getOne(id);
        if (role == null) throw new EntityNotFoundException("Role not found");
        role.setName(roleUpdateDto.getName());
        role.setPermissions(null);
        Role updated = roleRepository.save(role);
        List<Permission> permissions = new ArrayList<>();
        for (PermissionUpdateDto permissionUpdateDto : roleUpdateDto.getPermissionUpdateDtoList()) {
            Permission permission = permissionRepository.getByName(permissionUpdateDto.getName());
            permissions.add(permission);
        }
        updated.setPermissions(permissions);
        return entityToResponseDto(roleRepository.save(updated));

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
