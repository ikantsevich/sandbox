package com.exadel.sandbox.permission.repository;

import com.exadel.sandbox.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}

