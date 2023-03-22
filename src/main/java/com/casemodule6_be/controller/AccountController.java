package com.casemodule6_be.controller;

import com.casemodule6_be.dto.AccountToken;
import com.casemodule6_be.dto.ForgotPassword;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.dto.ChangePassword;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.AccountService;
import com.casemodule6_be.service.EmailService;
import com.casemodule6_be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@CrossOrigin("*")
@RequestMapping("/account")
@PropertySource("classpath:application.properties")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;
    @Autowired
    EmailService emailService;

    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;


    //Hiển thị tất cả user
    @GetMapping
    public List<Account> showAllAccount() {
        return accountService.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<String> findByEmail(@PathVariable String email) {
        Account account = accountService.findByEmail(email);
        if (account != null) {
            return new ResponseEntity<>(account.getEmail(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Account> findByName(@PathVariable String name) {
        return new ResponseEntity<>(accountService.findAccountByName(name), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Account> updateAccount(@RequestPart(value = "file", required = false) MultipartFile file,
                                                 @RequestBody Account account) {
        Account updateAccount = accountService.findById(account.getId());
        if (updateAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
                account.setAvatar("/img/" + fileName);
                accountService.save(account);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        account.setAvatar("/img/avatar.jpg");
        updateAccount.setPhone(account.getPhone());
        updateAccount.setName(account.getName());
        return new ResponseEntity<>(accountService.save(updateAccount), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {

        Account account = accountService.findById(changePassword.getId());

        if (changePassword.getNewPassword().equals("") || changePassword.getConfirmPassword().equals("")) {
            return new ResponseEntity<>("No blank", HttpStatus.NOT_FOUND);
        }

        if (changePassword.getNewPassword().equals(account.getPassword())) {

            return new ResponseEntity<>("New password can not same current password", HttpStatus.NOT_FOUND);

        } else if (!changePassword.getConfirmPassword().equals(changePassword.getNewPassword())) {

            return new ResponseEntity<>("Wrong re-password", HttpStatus.NOT_FOUND);

        }
        account.setPassword(changePassword.getNewPassword());
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword) throws MessagingException {
        Account account = accountService.findByEmail(forgotPassword.getEmail());
        Account confirmEmail = accountService.findAccountByEmail(forgotPassword.getEmail());
        if (forgotPassword.getEmail().equals("")) {
            return new ResponseEntity<>("All fields can not be blank", HttpStatus.NOT_FOUND);
        }
        if (confirmEmail.equals(confirmEmail)) {
            emailService.forgotMail(account.getEmail(), "Rent Room send:", account.getPassword());
            return new ResponseEntity<>(confirmEmail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Wrong email", HttpStatus.NOT_FOUND);
        }
    }
}




