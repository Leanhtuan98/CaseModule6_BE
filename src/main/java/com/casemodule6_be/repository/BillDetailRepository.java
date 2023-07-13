package com.casemodule6_be.repository;

import com.casemodule6_be.dto.bill_detail.BillDetailProjection;
import com.casemodule6_be.model.BillDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDetailRepository extends PagingAndSortingRepository<BillDetail, Long> {
    List<BillDetail> findAllByBill_Id(long billId);


    @Query(nativeQuery = true, value = "SELECT * FROM bill_detail\n" +
            "WHERE room_id = :roomId AND check_in >= DATE_ADD(DATE_ADD(DATE(NOW()), INTERVAL 1 SECOND), INTERVAL -1 DAY);")
    List<BillDetail> findSchedule(@Param("roomId") long id);


    BillDetail save(BillDetail billDetail);

    @Query(nativeQuery = true,value = "select bill_detail.room_id as roomId, sum(bill_detail.amount_day) as amountDay from bill_detail \n" +
            " group by bill_detail.room_id\n" +
            " order by sum(bill_detail.amount_day) desc;")
    List<BillDetailProjection> findTopRent();


}



