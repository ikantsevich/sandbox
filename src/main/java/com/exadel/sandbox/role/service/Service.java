package com.exadel.sandbox.role.service;

import java.util.List;

public interface Service<Dto, ResDto> {
    List<ResDto> getAll();


    ResDto getById(Integer id);

    ResDto create(Dto dto);

    void deleteById(Integer id);

    ResDto update(Integer id, Dto dto);

}
