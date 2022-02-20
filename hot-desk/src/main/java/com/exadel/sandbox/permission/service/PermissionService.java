package com.exadel.sandbox.permission.service;

import com.exadel.sandbox.permission.dto.PermissionCreateDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;

import java.util.List;

public interface PermissionService {
    List<PermissionResponseDto> getAll();

    PermissionResponseDto getById(Long id);

    PermissionResponseDto create(PermissionCreateDto dto);

    void deleteById(Long id);

    PermissionResponseDto update(Long id, PermissionUpdateDto dto);
}
