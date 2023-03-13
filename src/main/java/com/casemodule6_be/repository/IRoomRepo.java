package com.casemodule6_be.repository;

import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room,Long> {
//    @Query("SELECT r from Room r  where r.account.id = :accountId")
    List<Room> findByAccountId(Long accountId);
    Room save(Room room);

    void deleteById (Long id);

    Room findByName(String name);



}
