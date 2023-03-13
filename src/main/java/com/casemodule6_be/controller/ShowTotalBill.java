package com.casemodule6_be.controller;

import com.casemodule6_be.dto.BillProjection;
import com.casemodule6_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/showTotalBill")
public class ShowTotalBill {
    @Autowired
    BillService billService;



    @GetMapping
    public List<BillProjection> showTotalBill(){
        return billService.showTotalBill();
    }


}
