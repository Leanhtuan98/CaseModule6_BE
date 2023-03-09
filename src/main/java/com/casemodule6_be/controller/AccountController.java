package com.casemodule6_be.controller;

import com.casemodule6_be.model.Account;
import com.casemodule6_be.dto.ChangePassword;
import com.casemodule6_be.service.impl.AccountService;
import com.casemodule6_be.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;

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

    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;


    //Hiển thị tất cả user
    @GetMapping
    public List<Account> showAllAccount() {
        return accountService.findAll();
    }


    //    Đăng kí user mới
    @PostMapping("/account")
    public void create(@RequestBody Account account) {
        accountService.save(account);
    }


    //    Hiển thị user muốn sửa
    @GetMapping("/account/{id}")
    public Account showAccountDetail(@PathVariable int id) {
        return accountService.findById(id).get();
    }


    //    Sửa user
    @PostMapping("/account/{id}")
    public void saveEdit(@RequestBody Account account) {
        accountService.save(account);

    }

    //    Xóa user
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        accountService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Account> updateAccount(@RequestPart(value = "file", required = false)MultipartFile file,
                                           @RequestPart("account") Account account,
                                           @PathVariable Long id) {
        Optional<Account> updateAccount = accountService.findById(id);
        if (!updateAccount.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateAccount.get().setAvatar(displayLink + fileName);
        }
        updateAccount.get().setPhone(account.getPhone());
        updateAccount.get().setEmail(account.getEmail());
        updateAccount.get().setName(account.getName());
        return new ResponseEntity<>(accountService.save(updateAccount.get()),HttpStatus.OK);
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id,
                                            @RequestBody ChangePassword changePassword) {
        Account account = accountService.findById(id).get();

        if (changePassword.getNewPass().equals("") || changePassword.getConfirmPass().equals("")) {
            return new ResponseEntity<>("No blank", HttpStatus.NOT_FOUND);
        }

        if (changePassword.getNewPass().equals(account.getPassword())) {

            return new ResponseEntity<>("New password can not same current password",HttpStatus.NOT_FOUND);

        } else if (!changePassword.getConfirmPass().equals(changePassword.getNewPass())) {

            return new ResponseEntity<>("Wrong re-password",HttpStatus.NOT_FOUND);

        }
        account.setPassword(changePassword.getNewPass());
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


}
