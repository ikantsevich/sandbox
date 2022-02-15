package com.exadel.hotdesk.role.controller;

import com.exadel.hotdesk.role.service.CrudService;
import com.exadel.hotdesk.role.dto.RoleCreateDto;
import com.exadel.hotdesk.role.dto.RoleResponseDto;
import com.exadel.hotdesk.role.dto.RoleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final CrudService<RoleCreateDto, RoleUpdateDto, RoleResponseDto> roleService;

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
}