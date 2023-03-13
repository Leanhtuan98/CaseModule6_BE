package com.casemodule6_be.controller;

import com.casemodule6_be.dto.RegisterForm;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Role;
import com.casemodule6_be.service.EmailService;
import com.casemodule6_be.service.AccountService;
import com.casemodule6_be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RegisterController {
    @Autowired
    JwtService jwtService;

    @Autowired
    AccountService accountService;
    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm account) {
        if (account.getName().equals("") || account.getPassword().equals("") || account.getEmail().equals("")) {
            return new ResponseEntity<>("No blank!!!", HttpStatus.CONFLICT);
        }
        if (accountService.findAccountByName(account.getName()) == null &&
                accountService.findAccountByEmail(account.getEmail()) == null) {
            if (account.getPassword().length() < 6 || account.getPassword().length() > 8) {
                return new ResponseEntity<>("Password must be 6 to 8 characters", HttpStatus.OK);
            }
            if (account.getPassword().equals(account.getRePassword())) {
                Account accountCreate = new Account();
                accountCreate.setName(account.getName());
                accountCreate.setPassword(account.getPassword());
                accountCreate.setEmail(account.getEmail());
                accountCreate.setPhone(account.getPhone());
                accountCreate.setAvatar("images/avatar/d5e500cc4db9a1b28372cd9d9166ea89.jpg");
                List<Role> roles = new ArrayList<>();
                Role role = new Role();
                role.setId(2L);
                roles.add(role);
                emailService.sendEmail(account.getEmail(),"Thông báo","Tài khoản "+ account.getName()+ " đã được đăng kí với mật khẩu là :" + account.getPassword());
                accountService.save(accountCreate);
                Account account1 = accountService.findAccountByName(account.getName());
                account1.setRoles(roles);
                accountService.save(account1);
                return new ResponseEntity<>(account, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("Wrong re-pass", HttpStatus.CONFLICT);
            }
        } else if (accountService.findAccountByName(account.getName()) != null) {
            return new ResponseEntity<>("Username is existed", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Email is existed", HttpStatus.CONFLICT);
        }
    }
}
