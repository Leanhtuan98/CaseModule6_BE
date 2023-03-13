package com.casemodule6_be.repository;

import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT room.*\n" +
            "FROM room  JOIN category on room.category_id = category.id\n" +
            "left JOIN image on room.id = image.room_id\n" +
            "JOIN address ON room.address_id = address.id\n" +
            "left join order_detail on room.id = order_detail.room_id\n" +
            "WHERE category.name like :categoryName \n" +
            "and address.name like :addressName \n" +
            "and price BETWEEN :price1 and :price2 \n" +
            "and room.id = (\n" +
            "SELECT room_id from order_detail WHERE \n" +
            "check_in not BETWEEN cast(:checkin as DATE) AND CAST(:checkout as DATE)\n" +
            "and check_out not BETWEEN  cast(:checkin as DATE) AND CAST(:checkout  as DATE)\n" +
            "EXCEPT \n" +
            "(select room_id from order_detail where \n" +
            "check_in < cast(:checkin as DATE)\n" +
            "and check_out > cast(:checkin as DATE)))")
    List<Room> findAll(@Param("categoryName") String categoryName,
                       @Param("addressName") String addressName,
                       @Param("price1") double price1,
                       @Param("price2") double price2,
                       @Param("checkin") String checkin,
                       @Param("checkout") String checkout
    );

    @Query(nativeQuery = true, value = "SELECT  category.name FROM category\n" +
            "join room on category.id = room.category_id\n" +
            "WHERE room.id like :idRoom")
    String findCategoryName(@Param("idRoom") long idRoom);

}

