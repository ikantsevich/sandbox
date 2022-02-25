package com.exadel.sandbox.role.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.permission.entity.Permission;
import com.exadel.sandbox.permission.repository.PermissionRepository;
import com.exadel.sandbox.role.dto.RoleCreateDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.dto.RoleUpdateDto;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class RoleService extends BaseCrudService<Role, RoleResponseDto, RoleUpdateDto, RoleCreateDto, RoleRepository> {
    private final PermissionRepository permissionRepository;

    public RoleService(ModelMapper mapper, RoleRepository repository, PermissionRepository permissionRepository) {
        super(mapper, repository);
        this.permissionRepository = permissionRepository;
    }

    public ResponseEntity<RoleResponseDto> addPermissions(Long id, List<Long> permissions) {
        Role role = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));

        List<Permission> permissionsList = permissions.stream().map(permission -> permissionRepository.findById(permission).orElseThrow(() -> new EntityNotFoundException("Permission with id " + permission + " not found"))).collect(Collectors.toList());

        role.setPermissions(permissionsList);
        return ResponseEntity.ok(mapper.map(repository.save(role), RoleResponseDto.class));
    }
}