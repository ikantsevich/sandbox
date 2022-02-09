package com.exadel.sandbox.repositories;

import com.exadel.sandbox.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {

}
