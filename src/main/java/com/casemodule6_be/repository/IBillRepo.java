package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IBillRepo extends PagingAndSortingRepository<Bill,Long> {

}
