package com.casemodule6_be.repository;

import com.casemodule6_be.model.Orders;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrdersRepo extends PagingAndSortingRepository<Orders,Long> {
}
