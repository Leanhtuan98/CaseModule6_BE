package com.casemodule6_be.service;

import com.casemodule6_be.dto.BillProjection;
import com.casemodule6_be.repository.IBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillService {
    @Autowired
    IBillRepo iBillRepo;
    public List<BillProjection> showTotalBill(){
        return iBillRepo.showTotalBill();
    }

}
