package com.casemodule6_be.repository;

import com.casemodule6_be.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAccountRepo extends PagingAndSortingRepository<Account, Long> {
    Optional<Account> findByName(String name);
    Optional<Account> findByPhone(String phone);
    Account findAccountByName(String name);
    Optional<Account> findById(Long id);

    Account findAccountByEmail(String email);
    Account findByEmail(String email);
    Page<Account> findAccountByNameContaining(Pageable pageable, String name);

    Page<Account> findAccountByRoles_Id(Pageable pageable, Long id);


}
