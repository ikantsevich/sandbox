package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;
import com.exadel.sandbox.mapper.Mapper;
import com.exadel.sandbox.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final Service<RoleDto> roleService;

    @GetMapping("list")
    List<RoleDto> getRoles() {
       return roleService.getAll();
    }

    @GetMapping("{id}")
    RoleDto getRoleById(@PathVariable("id") Integer id) {
       return roleService.getById(id);
    }

    @PutMapping()
    RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Integer id) {
       roleService.deleteById(id);
    }

    @PostMapping("{id}")
    RoleDto updateRole(@PathVariable("id") Integer id, @RequestBody RoleDto roleDto) {
        return roleService.updateById(id,roleDto);
    }
}
