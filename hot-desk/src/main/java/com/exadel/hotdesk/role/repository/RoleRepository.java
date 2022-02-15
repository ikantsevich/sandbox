package com.exadel.hotdesk.role.repository;

import com.exadel.hotdesk.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}