package com.exadel.sandbox.permission.service;

import com.exadel.sandbox.permission.dto.PermissionCreateDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.permission.repository.PermissionRepository;
import com.exadel.sandbox.role.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements CrudService<PermissionCreateDto, PermissionUpdateDto, PermissionResponseDto> {
    private final PermissionRepository permissionRepository;
    private final ModelMapper permissionMapper;

    @Override
    public List<PermissionResponseDto> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionResponseDto> perResponseDtos = new ArrayList<>();
        for (Permission permission : permissions) {
            perResponseDtos.add(permissionMapper.map(permission, PermissionResponseDto.class));
        }
        return perResponseDtos;
    }

    @Override
    public PermissionResponseDto getById(Long id) {
        Optional<Permission> byId = permissionRepository.findById(id);
        Permission permission = byId.orElseThrow(() -> new EntityNotFoundException("Permission not found"));
        return permissionMapper.map(permission, PermissionResponseDto.class);
    }

    @Override
    public PermissionResponseDto create(PermissionCreateDto permissionCreateDto) {
        return permissionMapper.map(permissionRepository.save(permissionMapper.map(permissionCreateDto, Permission.class)), PermissionResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public PermissionResponseDto update(Long id, PermissionUpdateDto permissionUpdateDto) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permission not found"));
        permission.setModified(LocalDateTime.now());
        permission.setId(id);
        permission.setName(permissionUpdateDto.getName());
        permissionRepository.save(permission);
        return permissionMapper.map(permission, PermissionResponseDto.class);
    }
}
