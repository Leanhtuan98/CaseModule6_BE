package com.casemodule6_be.repository;


import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


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



}
