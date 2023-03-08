package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepo extends PagingAndSortingRepository<Bill,Long> {
//    Bill findTop(String username);
//    @Query(nativeQuery = true, value =
//            "select * from account where username=:username")
//    Account checkUser(@Param("username") String username);

}
