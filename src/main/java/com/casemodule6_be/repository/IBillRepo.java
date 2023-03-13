package com.casemodule6_be.repository;

import com.casemodule6_be.dto.BillProjection;
import com.casemodule6_be.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IBillRepo extends PagingAndSortingRepository<Bill,Long> {

    @Query(nativeQuery = true,value = "SELECT (month(bill.date)) as month, (sum(bill.total)) as toTalBill\n" +
            "FROM bill\n" +
            "GROUP BY month(bill.date)")
    List<BillProjection> showTotalBill();
}
