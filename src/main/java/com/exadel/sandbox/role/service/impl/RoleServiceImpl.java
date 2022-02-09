package com.exadel.sandbox.role.service.impl;

import com.exadel.sandbox.permission.dto.PerBaseDto;
import com.exadel.sandbox.permission.dto.PerResponseDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.role.dto.RoleBaseDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.role.service.Service;
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
public class RoleServiceImpl implements Service<RoleBaseDto, RoleResponseDto> {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public List<RoleResponseDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
        for (Role role : roles) {
            roleResponseDtos.add(mapAllF(role));
        }
        return roleResponseDtos;
    }

    @Override
    public RoleResponseDto getById(Integer id) {
        Optional<Role> byId = roleRepository.findById(id);
        return mapAllF(byId.orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Override
    public RoleResponseDto create(RoleBaseDto roleBaseDto) {
        Role role = mapper.map(roleBaseDto, Role.class);
        List<Permission> permissions = new ArrayList<>();
        if (roleBaseDto.getPerBaseDtos() != null) {
            for (PerBaseDto perBaseDto : roleBaseDto.getPerBaseDtos()) {
                permissions.add(mapper.map(perBaseDto, Permission.class));
            }
            role.setPermissions(permissions);
        }
        return mapAllF(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDto update(Integer id, RoleBaseDto roleBaseDto) {
        Optional<Role> byId = roleRepository.findById(id);
        Role role = byId.orElseThrow(() -> new RuntimeException("Role not found"));
        List<Permission> permissions = new ArrayList<>();
        role.setId(id);
        role.setName(roleBaseDto.getName());
        for (PerBaseDto perBaseDto : roleBaseDto.getPerBaseDtos()) {
            permissions.add(mapper.map(perBaseDto, Permission.class));
        }
        role.setPermissions(permissions);
        return mapAllF(role);

    }

    private RoleResponseDto mapAllF(Role role) {
        RoleResponseDto roleResponseDto = mapper.map(role, RoleResponseDto.class);
        if (role.getPermissions() != null) {
            List<PerResponseDto> perResponseDtos = new ArrayList<>();
            for (Permission permission : role.getPermissions()) {
                perResponseDtos.add(mapper.map(permission, PerResponseDto.class));
            }
            roleResponseDto.setPerResponseDtoList(perResponseDtos);
        }
        return roleResponseDto;
    }


}
