package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepo extends PagingAndSortingRepository<Bill,Long> {



}
