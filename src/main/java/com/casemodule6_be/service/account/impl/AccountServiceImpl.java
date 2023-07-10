package com.casemodule6_be.service.account.impl;

import com.casemodule6_be.model.Account;
import com.casemodule6_be.repository.AccountRepository;

import com.casemodule6_be.service.account.AccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Component
public class AccountServiceImpl implements UserDetailsService, AccountService {
   private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByName(username);
        return new User(account.getName(), account.getPassword(), account.getRoles());
    }



    public List<Account> findAll() {
        return  accountRepository.findAll();
    }


    public Account findById(Long id) {
        return accountRepository.findById(id).get();
    }


    public void delete(Long id) {
        accountRepository.deleteById(id);

    }

    public Account findAccountByName(String name) {
        return accountRepository.findAccountByName(name);
    }


    public Account save(Account account) {
        return accountRepository.save(account);
    }
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
