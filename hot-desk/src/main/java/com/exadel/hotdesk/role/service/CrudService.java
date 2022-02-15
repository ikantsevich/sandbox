package com.exadel.hotdesk.role.service;

import java.util.List;

public interface CrudService<CreateDto, UpdateDto, ResDto> {
    List<ResDto> getAll();

    ResDto getById(Long id);

    ResDto create(CreateDto dto);

    void deleteById(Long id);

    ResDto update(Long id, UpdateDto dto);
}