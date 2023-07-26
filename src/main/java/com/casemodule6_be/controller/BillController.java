package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.bill.BillRequest;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.service.bill.BillService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(Constant.PREFIX_API_URL +"/bill")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;
    private final Logger L = LoggerFactory.getLogger(RoomController.class);


//    @GetMapping(value = "/showBill", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<BillProjection> showTotalBill() {
//        L.info("[GET] {}: show bill", Constant.PREFIX_API_URL + "/bill/showBill");
//        return billService.showTotalBill();
//    }

    @GetMapping(value = "/showBillByAccountId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bill> billByUserId(@RequestParam Long accountId) {
        L.info("[GET] {}: show bill by user ID", Constant.PREFIX_API_URL + "/bill/showBillByUserId");
        return billService.findBillByUserId(accountId);
    }


    @PostMapping("/save")
    public ResponseEntity<Bill> save(@RequestBody BillRequest billRequest) {
        L.info("[POST] {}: save bill and bill_detail ", Constant.PREFIX_API_URL + "/bill/save");
        return ResponseEntity.ok().body(billService.save(billRequest));
    }

}
