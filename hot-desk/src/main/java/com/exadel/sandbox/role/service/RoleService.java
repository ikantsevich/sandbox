package com.exadel.sandbox.role.service;

import java.util.List;

public interface RoleService<CreateDto, UpdateDto, ResDto> {
    List<ResDto> getAll();

    ResDto getById(Long id);

    ResDto create(CreateDto dto);

    void deleteById(Long id);

    ResDto update(Long id, UpdateDto dto);

    ResDto addPermissions(Long id, List<Long> permissions);
}