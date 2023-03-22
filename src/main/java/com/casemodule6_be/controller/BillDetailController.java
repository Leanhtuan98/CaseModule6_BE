package com.casemodule6_be.controller;

import com.casemodule6_be.dto.BillDTO;
import com.casemodule6_be.dto.DataDTO;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.service.BillDetailService;
import com.casemodule6_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/billdetails")
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;

    @Autowired
    BillService billService;

    @GetMapping("/findByBillId")
    public List<BillDetail> findBillDetailByBillId(@RequestParam long billId) {
        return billDetailService.findBillDetailByBillId(billId);
    }

    @GetMapping("/schedule/{roomid}")
    public List<BillDetail> findSchedule(@PathVariable long roomid) {
        return billDetailService.findSchedule(roomid);
    }



    @PostMapping("/post")
    public void save(@RequestBody BillDTO billDTO){
         billService.save(billDTO);
    }

}
