package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Role;
import com.exadel.sandbox.repositories.RoleRepository;
import com.exadel.sandbox.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(int name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(int id, Role role) {
        if (roleRepository.existsById(id))
        {
            throw new RuntimeException("Role not found");
        }
        role.setId(id);
        return roleRepository.save(role);
    }
}
