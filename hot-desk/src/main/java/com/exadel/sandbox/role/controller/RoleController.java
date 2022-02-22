package com.exadel.sandbox.role.controller;

import com.exadel.sandbox.role.dto.RoleCreateDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.dto.RoleUpdateDto;
import com.exadel.sandbox.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService<RoleCreateDto, RoleUpdateDto, RoleResponseDto> roleService;

    @GetMapping("list")
    List<RoleResponseDto> getRoles() {
        return roleService.getAll();
    }

    @GetMapping("{id}")
    RoleResponseDto getRoleById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }

    @PostMapping()
    RoleResponseDto createRole(@RequestBody RoleCreateDto roleCreateDto) {
        return roleService.create(roleCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        roleService.deleteById(id);
    }

    @PutMapping("{id}")
    RoleResponseDto updateRole(@PathVariable("id") Long id, @RequestBody RoleUpdateDto roleUpdateDto) {
        return roleService.update(id, roleUpdateDto);
    }

    @PutMapping("{id}/add-permissions")
    RoleResponseDto addPermissions(@PathVariable("id") Long id,
                                   @RequestBody List<Long> permissions) {
        return roleService.addPermissions(id, permissions);
    }
}