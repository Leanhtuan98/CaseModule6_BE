//package com.casemodule6_be.controller;
//
//import com.casemodule6_be.dto.AccountToken;
//import com.casemodule6_be.model.Account;
//import com.casemodule6_be.service.account.impl.AccountServiceImpl;
//import com.casemodule6_be.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping
//public class LoginController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtService jwtService;
//
//    @Autowired
//    AccountServiceImpl accountServiceImpl;
//
//    @PostMapping("/login")
//    public AccountToken login(@RequestBody Account account){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(account.getName(), account.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtService.createToken(authentication);
//        Account account1 = accountServiceImpl.findAccountByName(account.getName());
//        AccountToken accountToken = new AccountToken(account1.getId(), account1.getName(),account1.getPhone(),account1.getEmail(), token,account1.getAvatar(), account1.getRoles());
//        return accountToken;
//    }
//}
