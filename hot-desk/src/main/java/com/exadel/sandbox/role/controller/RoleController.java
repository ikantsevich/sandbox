package com.exadel.sandbox.role.controller;

import com.exadel.sandbox.role.service.RoleService;
import dtos.role.dto.RoleCreateDto;
import dtos.role.dto.RoleResponseDto;
import dtos.role.dto.RoleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("list")
    ResponseEntity<List<RoleResponseDto>> getRoles() {
        return roleService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<RoleResponseDto> getRoleById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }

    @PostMapping()
    ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleCreateDto roleCreateDto) {
        return roleService.create(roleCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<RoleResponseDto> updateRole(@PathVariable("id") Long id, @RequestBody RoleUpdateDto roleUpdateDto) {
        return roleService.update(id, roleUpdateDto);
    }

    @PutMapping("{id}/add-permissions")
    ResponseEntity<RoleResponseDto> addPermissions(@PathVariable("id") Long id,
                                                   @RequestBody List<Long> permissions) {
        return roleService.addPermissions(id, permissions);
    }
}