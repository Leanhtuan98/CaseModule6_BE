package com.casemodule6_be.service;

import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.repository.IBillDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillDetailService {
    @Autowired
    IBillDetailRepo iBillDetailRepo;

    public List<BillDetail> findBillDetailByBillId(long billId) {
      return   iBillDetailRepo.findAllByBill_Id(billId);
    }
}
