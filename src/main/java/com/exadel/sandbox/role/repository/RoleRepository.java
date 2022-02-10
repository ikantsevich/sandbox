package com.exadel.sandbox.role.repository;

import com.exadel.sandbox.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
