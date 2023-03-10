package com.casemodule6_be.repository;
import com.casemodule6_be.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface IIMageRepo extends PagingAndSortingRepository<Image,Long> {
}
