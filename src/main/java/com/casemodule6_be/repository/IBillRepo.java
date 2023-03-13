package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBillRepo extends PagingAndSortingRepository<Bill,Long> {
}
