package com.casemodule6_be.service.bill_detail;

import com.casemodule6_be.model.BillDetail;

import java.util.List;

public interface BillDetailService {
    BillDetail findByid(long id);

    BillDetail save(BillDetail billDetail);
    List<BillDetail> findBillDetailByBillId(long billId);
    List<BillDetail> findSchedule(long roomID);
    List<BillDetail> showAll();
}
