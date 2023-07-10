package com.casemodule6_be.service.bill;

import com.casemodule6_be.dto.BillDTO;
import com.casemodule6_be.model.Bill;

import java.util.List;

public interface BillService {
//    List<BillProjection> showTotalBill();

    List<Bill> findBillByAccountId(Long accountId);

    Bill save(BillDTO billDTO);

}
