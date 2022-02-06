package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;
import com.exadel.sandbox.mapper.RoleMapper;
import com.exadel.sandbox.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping("list")
    List<RoleDto> getRoles() {
        List<Role> roles = roleService.getRoles();
        return roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    RoleDto getRoleById(@PathVariable("id") int id){
        Role role = roleService.getRoleById(id);
        return roleMapper.toDto(role);
    }
    @PutMapping()
    RoleDto createRole(@RequestBody RoleDto roleDto){
        Role role = roleMapper.toEntity(roleDto);
        role = roleService.create(role);
        return roleMapper.toDto(role);
    }
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") int id){
        roleService.deleteById(id);
    }

    @PostMapping("{id}")
    RoleDto updateRole(@PathVariable("id") int id,
                               @RequestBody RoleDto roleDto){
        Role role = roleMapper.toEntity(roleDto);
        role = roleService.update(id, role);
        return roleMapper.toDto(role);
    }
}
