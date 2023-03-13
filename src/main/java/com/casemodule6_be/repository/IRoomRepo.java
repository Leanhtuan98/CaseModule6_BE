package com.casemodule6_be.repository;

import com.casemodule6_be.dto.RoomHostDto;
import com.casemodule6_be.dto.RoomShowDTO;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room,Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT room.name, room.price, room.description,room.address_room as address, \n" +
            "category.name as typeroom, image.name as img\n" +
            "FROM room LEFT JOIN category on room.category_id = category.id\n" +
            "left JOIN image on room.id = image.room_id \n" +
            "JOIN address ON room.address_id = address.id\n" +
            "left JOIN order_detail on room.id = order_detail.room_id")
    List<RoomHostDto> showALL();



    @Query(nativeQuery = true, value = "SELECT room.name, room.price, room.description,room.address_room as address, category.name as typeroom, image.name as img\n"+
            " FROM room JOIN category on room.category_id = category.id\n" +
            "            JOIN image on room.id = image.room_id\n" +
            "JOIN address on room.address_id = address.id \n" +
            "WHERE category.name like concat('%',:categoryName,'%') \n" +
            "and address.name LIKE concat('%',:addressName,'%') \n"
            +
            "and room.price BETWEEN :price1 and :price2")
    List<RoomHostDto> findAll(@Param("categoryName") String categoryName,
                       @Param("addressName") String addressName,
                       @Param("price1" ) double price1,
                       @Param("price2") double price2
                       );


    List<Room> findAllByCategory_NameAndAddress_NameAndPriceBetween(String categoryName, String addressName, double price1, double price2);
}