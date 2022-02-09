package com.exadel.sandbox.permission.service;

import com.exadel.sandbox.permission.dto.PerBaseDto;
import com.exadel.sandbox.permission.dto.PerResponseDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.permission.repository.PermissionRepository;
import com.exadel.sandbox.role.service.Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements Service<PerBaseDto, PerResponseDto> {
    private final PermissionRepository permissionRepository;
    private final ModelMapper permissionMapper;

    @Override
    public List<PerResponseDto> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        if (permissions.size() > 0) {
            List<PerResponseDto> perResponseDtos = new ArrayList<>();
            for (Permission permission : permissions) {
                perResponseDtos.add(permissionMapper.map(permission, PerResponseDto.class));
            }
            return perResponseDtos;
        }
        return new ArrayList<>();
    }

    @Override
    public PerResponseDto getById(Integer id) {
        Optional<Permission> byId = permissionRepository.findById(id);
        Permission permission = byId.orElseThrow(() -> new RuntimeException("Permission not found"));
        return permissionMapper.map(permission, PerResponseDto.class);
    }

    @Override
    public PerResponseDto create(PerBaseDto perBaseDto) {
        return permissionMapper.map(permissionRepository.save(permissionMapper.map(perBaseDto, Permission.class)), PerResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public PerResponseDto update(Integer id, PerBaseDto perBaseDto) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setModified(new Date());
        permission.setId(id);
        permission.setName(perBaseDto.getName());
        permissionRepository.save(permission);
        return permissionMapper.map(permission, PerResponseDto.class);
    }
}
