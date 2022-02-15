package com.exadel.hotdesk.permission.repository;

import com.exadel.hotdesk.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission getByName(String delete);
}