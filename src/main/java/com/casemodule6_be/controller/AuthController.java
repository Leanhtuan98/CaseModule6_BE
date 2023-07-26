package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.user.LoginRequest;
import com.casemodule6_be.dto.user.RegisterRequest;
import com.casemodule6_be.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(Constant.PREFIX_API_URL +"/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
       return  ResponseEntity.ok().body( userService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok().body( userService.login(loginRequest));
    }

}
