package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;
import com.exadel.sandbox.mapper.Mapper;
import com.exadel.sandbox.repositories.RoleRepository;
import com.exadel.sandbox.service.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class RoleServiceImpl implements Service<RoleDto> {
    private final RoleRepository roleRepository;
    private final Mapper<Role, RoleDto> roleMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto getById(Integer id) {
        Role role = roleRepository.getOne(id);
        if (role == null)
            throw new RuntimeException("Permission not found");
        else
            return roleMapper.toDto(role);
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }

    @Override
    public void deleteById(Integer id) {
        getById(id);//throws exception if there is no role with curr id
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto updateById(Integer id, RoleDto roleDto) {
        getById(id);
        Role role = roleMapper.toEntity(roleDto);
        role.setRole_id(id);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }
}
