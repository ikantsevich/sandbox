package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.PermissionDto;
import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Permission;
import com.exadel.sandbox.mapper.Mapper;
import com.exadel.sandbox.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final Service<PermissionDto> permissionService;

    @GetMapping("list")
    List<PermissionDto> getPermissions() {
        return permissionService.getAll();
    }

    @GetMapping("{id}")
    PermissionDto getPermissionById(@PathVariable("id") Integer id) {
        return permissionService.getById(id);
    }

    @PutMapping()
    PermissionDto createPermission(@RequestBody PermissionDto permissionDto) {
        return permissionService.create(permissionDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Integer id) {
        permissionService.deleteById(id);
    }

    @PostMapping("{id}")
    PermissionDto updateRole(@PathVariable("id") Integer id, @RequestBody PermissionDto permissionDto) {
        return permissionService.updateById(id,permissionDto);
    }
}
