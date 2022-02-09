package com.exadel.sandbox.role.controller;

import com.exadel.sandbox.role.dto.RoleBaseDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final Service<RoleBaseDto, RoleResponseDto> roleService;

    @GetMapping("list")
    List<RoleResponseDto> getRoles() {
        return roleService.getAll();
    }

    @GetMapping("{id}")
    RoleResponseDto getRoleById(@PathVariable("id") Integer id) {
        return roleService.getById(id);
    }

    @PostMapping()
    RoleResponseDto createRole(@RequestBody RoleBaseDto roleBaseDto) {
        return roleService.create(roleBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Integer id) {
        roleService.deleteById(id);
    }

    @PutMapping("{id}")
    RoleResponseDto updateRole(@PathVariable("id") Integer id, @RequestBody RoleBaseDto roleBaseDto) {
        return roleService.update(id, roleBaseDto);
    }
}
