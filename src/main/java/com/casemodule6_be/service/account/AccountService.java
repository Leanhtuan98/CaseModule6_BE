package com.casemodule6_be.service.account;

import com.casemodule6_be.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Long id);
    void delete(Long id);
    Account findAccountByName(String name);
    Account save(Account account);
    Account findAccountByEmail(String email);
    Account findByEmail(String email);
}
