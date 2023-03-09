package com.casemodule6_be.repository;

import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepo extends PagingAndSortingRepository<Category,Long> {
}
