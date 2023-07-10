package com.casemodule6_be.repository;

import com.casemodule6_be.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByName(String name);

    Optional<Account> findById(Long id);

    Account findAccountByEmail(String email);

    Account findByEmail(String email);


    @Query(nativeQuery = true, value = "select account.* from room join account on account.id = room.account_id where room.id = :id limit 1;")
    Account findAccountByRoomId(@Param("id") long id);
}
