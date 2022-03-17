package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import com.exadel.sandbox.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Employee with email " + email + " not found")
                );

        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        List<Role> roles = employee.getRoles();
        roles.forEach(role -> authoritySet.add(new SimpleGrantedAuthority(role.getName())));

        return new User(email,
                employee.getPassword(),
                true,
                true,
                true,
                employee.getEmploymentEnd() == null,
                authoritySet);
    }
}