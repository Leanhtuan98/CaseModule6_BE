package com.casemodule6_be.service.bill;

import com.casemodule6_be.dto.bill.BillRequest;
import com.casemodule6_be.model.Bill;

import java.util.List;

public interface BillService {

    List<Bill> findBillByAccountId(Long accountId);

    Bill save(BillRequest billRequest);

}
