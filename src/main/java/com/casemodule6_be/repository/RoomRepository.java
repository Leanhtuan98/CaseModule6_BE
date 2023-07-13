package com.casemodule6_be.repository;


import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {



    Room save(Room room);

    void deleteById(Long id);



//    @Query(nativeQuery = true, value = "select * from room where room.id in(\n" +
//            "SELECT DISTINCT room.id\n" +
//            "FROM room  JOIN category on room.category_id = category.id\n" +
//            "left JOIN image on room.id = image.room_id\n" +
//            "JOIN address ON room.address_id = address.id\n" +
//            "left join bill_detail on room.id = bill_detail.room_id\n" +
//            "WHERE category.name like :categoryName \n" +
//            "and address.name like :addressName \n" +
//            "and price BETWEEN  :price1 and  :price2 \n" +
//            "and room.id in  (\n" +
//            "SELECT room.id from  bill_detail  " +
//            "WHERE \n" +
//            "check_in not BETWEEN cast(:checkin as DATE) AND CAST(:checkout as DATE)\n" +
//            "and check_out not BETWEEN  cast(:checkin as DATE) AND CAST(:checkout  as DATE)\n" +
//            "except \n" +
//            "(select room_id from bill_detail where \n" +
//            "check_in <= cast(:checkin as DATE)\n" +
//            "and check_out >= cast(:checkin as DATE))" +
//            "except " +
//            "(select room_id from bill_detail where " +
//            "check_in >= cast(:checkin as date )" +
//            "and check_out <= cast(:checkin as date ))))")
//    List<Room> findAll(@Param("categoryName") String categoryName,
//                       @Param("addressName") String addressName,
//                       @Param("price1") double price1,
//                       @Param("price2") double price2,
//                       @Param("checkin") String checkin,
//                       @Param("checkout") String checkout
//    );



    @Query(nativeQuery = true, value ="select * from room r where r.address_id = :idAddress and r.category_id = :idCategory \n" +
            "and ( r.price between :minPrice and :maxPrice) \n" +
            "and r.id not in (select  b.room_id from bill_detail as b  \n" +
            "where (check_in between CAST(:checkIn AS DATE) and CAST(:checkOut AS DATE)) \n" +
            "and (check_out between CAST(:checkIn AS DATE) AND CAST(:checkOut AS DATE)))")
    List<Room> findForMany(@Param("idAddress") Long idAddress,
                           @Param("idCategory") Long idCategory,
                           @Param("minPrice") Double minPrice,
                           @Param("maxPrice") Double maxPrice,
                           @Param("checkIn") String checkIn,
                           @Param("checkOut") String checkOut);


    @Query(nativeQuery = true, value = "SELECT  category.name FROM category\n" +
            "join room on category.id = room.category_id\n" +
            "WHERE room.id like :idRoom")
    String findCategoryName(@Param("idRoom") long idRoom);

}
