package com.casemodule6_be.service.user;

import com.casemodule6_be.dto.user.LoginRequest;
import com.casemodule6_be.dto.user.RegisterRequest;
import com.casemodule6_be.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);

    ResponseEntity<?> register(RegisterRequest registerRequest);
    ResponseEntity<?> login(LoginRequest loginRequest);


    void delete(Long id);



}
