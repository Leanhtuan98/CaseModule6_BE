package com.casemodule6_be.controller;

import com.casemodule6_be.dto.BillProjection;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/bill")
public class ShowTotalBill {
    @Autowired
    BillService billService;

    @GetMapping("/showBill")
    public List<BillProjection> showTotalBill() {
        return billService.showTotalBill();
    }

    @GetMapping("/showBillByAccountId")
    public List<Bill> billByAccountId(@RequestParam long accountId) {
        return billService.findBillByAccountId(accountId);
    }

}
