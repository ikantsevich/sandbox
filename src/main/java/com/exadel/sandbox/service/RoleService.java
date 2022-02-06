package com.exadel.sandbox.service;

import com.exadel.sandbox.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role create (Role role);
    Role getRoleById (int id);
    void deleteById(int id);
    Role update(int id,Role role);
    Role getRoleByName(int name);
}
