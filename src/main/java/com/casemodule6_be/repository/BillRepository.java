package com.casemodule6_be.repository;

import com.casemodule6_be.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

//    @Query(nativeQuery = true, value = "SELECT (month(bill.date)) as month, (sum(bill.total)) as toTalBill\n" +
//            "            FROM bill\n" +
//            "            GROUP BY month(bill.date)")
//    List<BillProjection> showTotalBill();


    List<Bill> findAllByUserId(Long accountId);



    Bill save(Bill bill);
}
