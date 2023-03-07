package com.casemodule6_be.repository;

import com.casemodule6_be.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room,Long> {
}
