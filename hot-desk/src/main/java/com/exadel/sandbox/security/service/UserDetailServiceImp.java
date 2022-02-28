package com.exadel.sandbox.security.service;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.security.configuration.JwtTokenProvider;
import com.exadel.sandbox.security.model.ApiResponse;
import com.exadel.sandbox.security.model.LoginDto;
import com.exadel.sandbox.security.model.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> byUsername = employeeRepository.findByUsername(username);
        if (byUsername.isEmpty())
            throw new UsernameNotFoundException(username);
        return new UserPrincipal(byUsername.get());
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                    loginDto.getPassword()));
            UserPrincipal user = (UserPrincipal) authenticate.getPrincipal();
            String token = jwtProvider.generateJwtToken(authenticate);
            return new ApiResponse("Token",true,token);
        } catch (BadCredentialsException e) {
            return new ApiResponse("UserName or Password is incorrect");
        }
    }
    public ApiResponse registerUser(UserRegisterDto userRegisterDto) {
        boolean existsByEmail = employeeRepository.existsByEmail(userRegisterDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("this email is already exists");
        }
        Employee user =new UserPrincipal().getEmployee();
        user.setUsername(userRegisterDto.getFirstName());
        user.setLastname(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByRole("USER").get()));
        employeeRepository.save(user);
        return new ApiResponse("Registered", true);
    }

}
