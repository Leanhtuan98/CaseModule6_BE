package com.casemodule6_be.controller;

import com.casemodule6_be.dto.DataDTO;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.dto.BillDTO;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/billdetails")
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    RoomService roomService;
    @Autowired
    EmailService emailService;


    @Autowired
    BillService billService;
@Autowired
    AccountService accountService;
    @GetMapping("/findByBillId")
    public List<BillDetail> findBillDetailByBillId(@RequestParam long billId) {
        return billDetailService.findBillDetailByBillId(billId);
    }

    @GetMapping("/schedule/{roomid}")
    public List<BillDetail> findSchedule(@PathVariable long roomid) {
        return billDetailService.findSchedule(roomid);
    }

@PostMapping("/status")
        public ResponseEntity<BillDetail> changeStatusBillDetail(@RequestParam("bdId") long id){
        BillDetail billDetail = billDetailService.findByid(id);
        billDetail.setStatus(false);
        return ResponseEntity.ok(billDetailService.save(billDetail));
}
@PostMapping("/email")
    public HttpStatus sendEmailforCanceling(@RequestParam("roomId") long id, @RequestBody BillDetail billDetail ){
Account account = roomService.findAccByRoomId(id);
emailService.sendEmail(account.getEmail(),"Thông báo","Bill" + billDetail.getId() + "vưa hủy phòng");
        return HttpStatus.OK;
}
    @PostMapping("/emailbooking/{accountId}")
    public HttpStatus sendEmailBooking(@PathVariable("accountId") long id, @RequestBody BillDTO dataDTO){
        Account account = accountService.findById(id);

        for (DataDTO dataDTO1: dataDTO.getData()
             ) { emailService.sendEmail(roomService.findAccByRoomId(dataDTO1.getId()).getEmail(),"Thông báo","Tài khoản " + account.getName() + " vưa đặt phòng số"+ dataDTO1.getId()
                +" của bạn. Checkin: " +dataDTO1.getCheckinDate()+",  "+"check out: "+dataDTO1.getCheckoutDate()+". Vui lòng truy cập web để xem chi tiết.");
        }

        return HttpStatus.OK;
    }

    @PostMapping
    public BillDetail save(@RequestBody BillDetail billDetail) {
        return billDetailService.save(billDetail);
    }

    @GetMapping("/showall")
    public List<BillDetail> showall() {
        return billDetailService.showAll();
    }



    @PostMapping("/post")
    public Bill save(@RequestBody BillDTO billDTO){

        return billService.save(billDTO);
    }

}
