package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {

//    @Query(nativeQuery = true, value = "SELECT (month(bill.date)) as month, (sum(bill.total)) as toTalBill\n" +
//            "            FROM bill\n" +
//            "            GROUP BY month(bill.date)")
//    List<BillProjection> showTotalBill();


    List<Bill> findAllByAccountId(Long accountId);



    Bill save(Bill bill);
}
