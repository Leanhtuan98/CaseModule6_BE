package com.casemodule6_be.service.account.impl;

import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.enums.RoleName;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.dto.user.JwtResponse;
import com.casemodule6_be.dto.user.LoginRequest;
import com.casemodule6_be.dto.user.RegisterMessage;
import com.casemodule6_be.dto.user.RegisterRequest;
import com.casemodule6_be.model.Role;
import com.casemodule6_be.model.User;
import com.casemodule6_be.repository.RoleRepository;
import com.casemodule6_be.repository.UserRepository;
import com.casemodule6_be.sercurity.jwt.JwtProvider;
import com.casemodule6_be.sercurity.userprincal.UserPrinciple;
import com.casemodule6_be.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
           return new ResponseEntity<>(new RegisterMessage("User is exists"), HttpStatus.OK);
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity<>(new RegisterMessage("Email is exists"), HttpStatus.OK);
        }
        User user = new User(registerRequest.getName(),
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));
        Set<String> strRoles = registerRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ADMIN).orElseThrow(() ->new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.PM).orElseThrow(() ->new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.USER).orElseThrow(() ->new RuntimeException("Role not found"));
                    roles.add(userRole);
                    break;
            }
        });
        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new RegisterMessage("Create sucessfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }

    @Override
    public void delete(Long id) {

    }
}
