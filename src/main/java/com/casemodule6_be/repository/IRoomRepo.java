package com.casemodule6_be.repository;

import com.casemodule6_be.dto.RoomDetailDTO;
import com.casemodule6_be.dto.RoomDetailProjection;
import com.casemodule6_be.dto.RoomProjection;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepo extends PagingAndSortingRepository<Room, Long> {
    @Query(nativeQuery = true, value = "SELECT bill_detail.room_id as id,room.name,\n" +
            "\t   ( GROUP_CONCAT(Distinct image.name)) as images, \n" +
            "\t    room.price ,\n" +
            "        (sum(bill_detail.amount_day)) as sum \n" +
            "FROM bill_detail JOIN room ON room.id = bill_detail.room_id\n" +
            "\t\t\t\tJOIN image ON image.room_id = room.id\n" +
            "GROUP BY bill_detail.room_id\n" +
            "ORDER BY sum(bill_detail.amount_day) desc\n" +
            "LIMIT 5")
    List<RoomProjection> findTopRent();

    List<Room> findByAccountId(Long accountId);

    Room save(Room room);

    void deleteById(Long id);

    Room findByName(String name);

    @Query(nativeQuery = true, value = "SELECT DISTINCT room.*\n" +
            "FROM room  JOIN category on room.category_id = category.id\n" +
            "left JOIN image on room.id = image.room_id\n" +
            "JOIN address ON room.address_id = address.id\n" +
            "left join bill_detail on room.id = bill_detail.room_id\n" +
            "WHERE category.name like :categoryName \n" +
            "and address.name like :addressName \n" +
            "and price BETWEEN  :price1 and  :price2 \n" +
            "and room.id in  (\n" +
            "SELECT room.id from  bill_detail  " +
            "WHERE \n" +
            "check_in not BETWEEN cast(:checkin as DATE) AND CAST(:checkout as DATE)\n" +
            "and check_out not BETWEEN  cast(:checkin as DATE) AND CAST(:checkout  as DATE)\n" +
            "EXCEPT \n" +
            "(select room_id from bill_detail where \n" +
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




    @Query(nativeQuery = true, value = "select r.id, r.name, r.address_room as addressRoom, r.price,\n" +
            " group_concat(i.name) as image,r.description\n" +
            "from room r left join image i on r.id = i.room_id\n" +
            "WHERE r.id =:id\n" +
            "GROUP BY r.id")
//    List<RoomDetailProjection> showDetail(@Param("id") Long id);
    RoomDetailProjection showDetail(@Param("id") Long id);


}
