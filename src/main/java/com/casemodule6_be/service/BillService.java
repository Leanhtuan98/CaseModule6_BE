package com.casemodule6_be.service;

import com.casemodule6_be.dto.BillProjection;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.repository.IAccountRepo;
import com.casemodule6_be.repository.IBillRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IBillRepo iBillRepo;
    @Autowired
    IAccountRepo iAccountRepo;


    public List<BillProjection> showTotalBill() {
        return iBillRepo.showTotalBill();
    }

    public List<Bill> findBillByAccountId(long accountId) {
        return iBillRepo.findAllByAccount_Id(accountId);
    }


}
