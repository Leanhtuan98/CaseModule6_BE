package com.casemodule6_be.repository;

import com.casemodule6_be.dto.RoomCount;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room, Long> {
    @Query(nativeQuery = true, value =
            "SELECT bill_detail.room_id as id, count(bill_detail.room_id) FROM bill_detail \n" +
                    "GROUP BY bill_detail.room_id\n" +
                    "ORDER BY bill_detail.room_id ASC")
    List<?> findTopListRent();
}
