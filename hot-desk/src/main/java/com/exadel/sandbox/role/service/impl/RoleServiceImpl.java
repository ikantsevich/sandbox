package com.exadel.sandbox.role.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.permission.repository.PermissionRepository;
import com.exadel.sandbox.role.dto.RoleCreateDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.dto.RoleUpdateDto;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService<RoleCreateDto, RoleUpdateDto, RoleResponseDto> {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper mapper;

    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto getById(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        return entityToResponseDto(byId.orElseThrow(() -> new EntityNotFoundException("Role not found")));
    }

    @Override
    public RoleResponseDto create(RoleCreateDto roleCreateDto) {
        Role role = mapper.map(roleCreateDto, Role.class);
        Role savedRole = roleRepository.save(role);
        return entityToResponseDto(savedRole);
    }

    @Override
    public void deleteById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setPermissions(null);
        roleRepository.save(role);
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDto update(Long id, RoleUpdateDto roleUpdateDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setName(roleUpdateDto.getName());
        role.setPermissions(null);
        Role updated = roleRepository.save(role);
        return entityToResponseDto(roleRepository.save(updated));
    }

    @Override
    public RoleResponseDto addPermissions(Long id, List<Long> permissions) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        List<Permission> permissionsList = permissions.stream().map(permission -> {
            return permissionRepository.findById(permission).orElseThrow(() -> new EntityNotFoundException("Permission with id " + permission + " not found"));
        }).collect(Collectors.toList());

        role.setPermissions(permissionsList);
        roleRepository.save(role);
        return entityToResponseDto(role);
    }

    private RoleResponseDto entityToResponseDto(Role role) {
        RoleResponseDto roleResponseDto = mapper.map(role, RoleResponseDto.class);
        if (role.getPermissions() != null) {
            List<PermissionResponseDto> perResponseDtos = role.getPermissions()
                    .stream()
                    .map(permission -> mapper.map(permission, PermissionResponseDto.class))
                    .collect(Collectors.toList());
            roleResponseDto.setPerResponseDtoList(perResponseDtos);
        }
        return roleResponseDto;
    }
}