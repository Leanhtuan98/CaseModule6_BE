package com.casemodule6_be.service.bill_detail.impl;

import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.repository.IBillDetailRepo;
import com.casemodule6_be.service.bill_detail.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {

    private final IBillDetailRepo iBillDetailRepo;

    public BillDetail findByid(long id) {
        return iBillDetailRepo.findById(id).get();
    }

    public BillDetail save(BillDetail billDetail) {
        return iBillDetailRepo.save(billDetail);
    }

    public List<BillDetail> findBillDetailByBillId(long billId) {
        return iBillDetailRepo.findAllByBill_Id(billId);
    }

    public List<BillDetail> findSchedule(long roomID) {
        return iBillDetailRepo.findSchedule(roomID);
    }


    public List<BillDetail> showAll() {
        return (List<BillDetail>) iBillDetailRepo.findAll();
    }
}



