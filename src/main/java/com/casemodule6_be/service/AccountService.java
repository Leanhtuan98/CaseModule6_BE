package com.casemodule6_be.service;

import com.casemodule6_be.model.Account;
import com.casemodule6_be.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Component
public class AccountService implements UserDetailsService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.findAccountByName(username);
        return new User(account.getName(), account.getPassword(), account.getRoles());
    }

    public AccountService() {
    }

    public List<Account> findAll() {
        return (List<Account>) iAccountRepo.findAll();
    }


    public Optional<Account> findById(long id) {
        return iAccountRepo.findById(id);
    }

    public void delete(long id) {
        iAccountRepo.deleteById(id);

    }

    public Account findAccountByName(String name) {
        return iAccountRepo.findAccountByName(name);
    }

    public boolean isAccountDuplicated(String name) {
        Account optionalAccount = iAccountRepo.findAccountByName(name);
        if (optionalAccount == null) {
            return false;
        } else {
            return true;
        }
    }

    public Account save(Account account) {
        return iAccountRepo.save(account);
    }
    public Account findAccountByEmail(String email) {
        return iAccountRepo.findAccountByEmail(email);
    }

}