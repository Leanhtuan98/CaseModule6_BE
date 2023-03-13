package com.casemodule6_be.repository;

import com.casemodule6_be.dto.RoomProjection;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room,Long> {
    @Query(nativeQuery = true,value = "SELECT bill_detail.room_id as id,room.name,\n" +
            "\t   ( GROUP_CONCAT(Distinct image.name)) as images, \n" +
            "\t    room.price ,\n" +
            "        (sum(bill_detail.amount_day)) as sum \n" +
            "FROM bill_detail JOIN room ON room.id = bill_detail.room_id\n" +
            "\t\t\t\tJOIN image ON image.room_id = room.id\n" +
            "GROUP BY bill_detail.room_id\n" +
            "ORDER BY sum(bill_detail.amount_day) desc\n" +
            "LIMIT 5")
    List<RoomProjection> findTopRent();

}
