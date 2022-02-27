package com.exadel.sandbox.permission.controller;

import com.exadel.sandbox.permission.service.PermissionService;
import dtos.permission.dto.PermissionCreateDto;
import dtos.permission.dto.PermissionResponseDto;
import dtos.permission.dto.PermissionUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping("list")
    ResponseEntity<List<PermissionResponseDto>> getPermissions() {
        return permissionService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<PermissionResponseDto> getPermissionById(@PathVariable("id") Long id) {
        return permissionService.getById(id);
    }

    @PostMapping()
    ResponseEntity<PermissionResponseDto> createPermission(@RequestBody PermissionCreateDto permissionCreateDto) {
        return permissionService.create(permissionCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        permissionService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<PermissionResponseDto> updateRole(@PathVariable("id") Long id, @RequestBody PermissionUpdateDto permissionUpdateDto) {
        return permissionService.update(id, permissionUpdateDto);
    }
}
