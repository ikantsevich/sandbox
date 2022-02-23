package com.exadel.sandbox.security.service;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> byUsername = employeeRepository.findByUsername(username);
        if (byUsername.isEmpty())
            throw new UsernameNotFoundException(username);
        return new UserPrincipal(byUsername.get());
    }
}
