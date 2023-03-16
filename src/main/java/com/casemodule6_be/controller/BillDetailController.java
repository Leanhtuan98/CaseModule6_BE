package com.casemodule6_be.controller;

import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/billdetails")
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;
    @GetMapping("/findByBillId")
    public List<BillDetail> findBillDetailByBillId(@RequestParam long billId) {
      return   billDetailService.findBillDetailByBillId(billId);
    }
}
