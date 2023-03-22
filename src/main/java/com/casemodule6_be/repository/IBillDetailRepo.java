package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBillDetailRepo extends PagingAndSortingRepository<BillDetail, Long> {
    List<BillDetail> findAllByBill_Id(long billId);


    @Query(nativeQuery = true, value = "SELECT * FROM bill_detail\n" +
            "WHERE room_id = :roomId AND check_in >= DATE_ADD(DATE_ADD(DATE(NOW()), INTERVAL 1 SECOND), INTERVAL -1 DAY);")
    List<BillDetail> findSchedule(@Param("roomId") long id);


    BillDetail save(BillDetail billDetail);


}



