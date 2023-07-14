package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.bill.BillRequest;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.service.account.impl.AccountServiceImpl;
import com.casemodule6_be.service.email.EmailService;
import com.casemodule6_be.service.bill.impl.BillServiceImpl;
import com.casemodule6_be.service.bill_detail.BillDetailService;
import com.casemodule6_be.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(Constant.PREFIX_API_URL + "/billDetails")
@RequiredArgsConstructor
public class BillDetailController {

    private final BillDetailService billDetailService;
    private final RoomService roomService;
    private final EmailService emailService;
    private final BillServiceImpl billServiceImpl;
    private final AccountServiceImpl accountServiceImpl;
    private final Logger L = LoggerFactory.getLogger(RoomController.class);

    @GetMapping("/findByBillId")
    public List<BillDetail> findBillDetailByBillId(@RequestParam long billId) {
        L.info("[GET] {}: find by bill id", Constant.PREFIX_API_URL + "/billDetails/findByBillId");
        return billDetailService.findBillDetailByBillId(billId);
    }

    @GetMapping("/schedule/{roomid}")
    public List<BillDetail> findSchedule(@PathVariable long roomid) {
        L.info("[GET] {}: find schedule", Constant.PREFIX_API_URL + "/billDetails/schedule");
        return billDetailService.findSchedule(roomid);
    }

    @PostMapping("/status")
    public ResponseEntity<BillDetail> changeStatusBillDetail(@RequestParam("bdId") long id) {
        BillDetail billDetail = billDetailService.findByid(id);
        billDetail.setStatus(Constant.DELETE_STATUS);
        return ResponseEntity.ok(billDetailService.save(billDetail));
    }

//    @PostMapping("/email")
//    public HttpStatus sendEmailforCanceling(@RequestParam("roomId") long id, @RequestBody BillDetail billDetail) {
//        Account account = roomService.findAccByRoomId(id);
//        emailService.sendEmail(account.getEmail(), "Thông báo", "Bill" + billDetail.getId() + "vưa hủy phòng");
//        return HttpStatus.OK;
//    }

//    @PostMapping("/emailBooking/{accountId}")
//    public HttpStatus sendEmailBooking(@PathVariable("accountId") long id, @RequestBody BillRequest dataDTO) {
//        Account account = accountServiceImpl.findById(id);
//
//        for (DataDTO dataDTO1 : dataDTO.getData()
//        ) {
//            emailService.sendEmail(roomService.findAccByRoomId(dataDTO1.getId()).getEmail(), "Thông báo", "Tài khoản " + account.getName() + " vưa đặt phòng số" + dataDTO1.getId()
//                    + " của bạn. Checkin: " + dataDTO1.getCheckinDate() + ",  " + "check out: " + dataDTO1.getCheckoutDate() + ". Vui lòng truy cập web để xem chi tiết.");
//        }
//
//        return HttpStatus.OK;
//    }

    @PostMapping
    public BillDetail save(@RequestBody BillDetail billDetail) {
        return billDetailService.save(billDetail);
    }

    @GetMapping("/showAll")
    public List<BillDetail> showAll() {
        return billDetailService.showAll();
    }




}
