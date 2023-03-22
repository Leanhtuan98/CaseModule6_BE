package com.casemodule6_be.repository;

import com.casemodule6_be.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

@Query(nativeQuery = true,value = "select account.* from room join account on account.id = room.account_id where room.id = :id limit 1;")
    Account findAccountByRoomId(@Param("id") long id);
}
