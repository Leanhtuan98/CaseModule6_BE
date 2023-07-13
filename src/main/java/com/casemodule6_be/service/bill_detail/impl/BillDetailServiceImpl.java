package com.casemodule6_be.service.bill_detail.impl;

import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.repository.BillDetailRepository;
import com.casemodule6_be.service.bill_detail.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;

    public BillDetail findByid(long id) {
        return billDetailRepository.findById(id).get();
    }

    public BillDetail save(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    public List<BillDetail> findBillDetailByBillId(long billId) {
        return billDetailRepository.findAllByBill_Id(billId);
    }

    public List<BillDetail> findSchedule(long roomID) {
        return billDetailRepository.findSchedule(roomID);
    }


    public List<BillDetail> showAll() {
        return (List<BillDetail>) billDetailRepository.findAll();
    }
}



