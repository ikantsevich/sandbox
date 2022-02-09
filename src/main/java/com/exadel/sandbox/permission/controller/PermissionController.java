package com.exadel.sandbox.permission.controller;

import com.exadel.sandbox.permission.dto.PerBaseDto;
import com.exadel.sandbox.permission.dto.PerResponseDto;
import com.exadel.sandbox.role.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final Service<PerBaseDto, PerResponseDto> permissionService;

    @GetMapping("list")
    List<PerResponseDto> getPermissions() {
        return permissionService.getAll();
    }

    @GetMapping("{id}")
    PerResponseDto getPermissionById(@PathVariable("id") Integer id) {
        return permissionService.getById(id);
    }

    @PostMapping()
    PerResponseDto createPermission(@RequestBody PerBaseDto perBaseDto) {
        return permissionService.create(perBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Integer id) {
        permissionService.deleteById(id);
    }

    @PutMapping("{id}")
    PerResponseDto updateRole(@PathVariable("id") Integer id, @RequestBody PerBaseDto perBaseDto) {
        return permissionService.update(id, perBaseDto);
    }
}
