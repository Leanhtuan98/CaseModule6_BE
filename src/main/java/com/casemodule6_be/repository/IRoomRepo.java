package com.casemodule6_be.repository;

import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room, Long> {
    @Query(nativeQuery = true, value =
            "SELECT bill_detail.room_id as id, count(bill_detail.room_id) FROM bill_detail \n" +
                    "GROUP BY bill_detail.room_id\n" +
                    "ORDER BY bill_detail.room_id ASC")
    List<?> findTopListRent();
=======
import java.util.Optional;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room,Long> {

>>>>>>> e628ddaeadceccf07def8c40a59a859a3cefeb05
}
