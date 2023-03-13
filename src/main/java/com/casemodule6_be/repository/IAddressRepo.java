package com.casemodule6_be.repository;

import com.casemodule6_be.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAddressRepo extends PagingAndSortingRepository<Address,Long> {
}
