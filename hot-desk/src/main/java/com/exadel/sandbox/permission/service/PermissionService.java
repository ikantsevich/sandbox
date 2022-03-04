package com.exadel.sandbox.permission.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.permission.dto.PermissionCreateDto;
import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.exadel.sandbox.permission.dto.PermissionUpdateDto;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.permission.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PermissionService extends BaseCrudService<Permission, PermissionResponseDto, PermissionUpdateDto, PermissionCreateDto, PermissionRepository>{
    public PermissionService(ModelMapper mapper, PermissionRepository repository) {
        super(mapper, repository);
    }
}