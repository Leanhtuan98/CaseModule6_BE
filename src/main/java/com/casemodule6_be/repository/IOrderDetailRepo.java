package com.casemodule6_be.repository;

import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrderDetailRepo extends PagingAndSortingRepository<OrderDetail,Long> {
}
