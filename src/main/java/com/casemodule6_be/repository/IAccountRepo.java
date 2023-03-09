package com.casemodule6_be.repository;

import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAccountRepo extends PagingAndSortingRepository<Account,Long> {
}
