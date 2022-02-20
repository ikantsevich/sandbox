package com.exadel.sandbox.permission.controller;

import com.exadel.sandbox.permission.dto.PermissionCreateDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import com.exadel.sandbox.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping("list")
    List<PermissionResponseDto> getPermissions() {
        return permissionService.getAll();
    }

    @GetMapping("{id}")
    PermissionResponseDto getPermissionById(@PathVariable("id") Long id) {
        return permissionService.getById(id);
    }

    @PostMapping()
    PermissionResponseDto createPermission(@RequestBody PermissionCreateDto permissionCreateDto) {
        return permissionService.create(permissionCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        permissionService.deleteById(id);
    }

    @PutMapping("{id}")
    PermissionResponseDto updateRole(@PathVariable("id") Long id, @RequestBody PermissionUpdateDto permissionUpdateDto) {
        return permissionService.update(id, permissionUpdateDto);
    }
}
