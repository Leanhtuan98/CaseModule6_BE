package com.casemodule6_be.service.bill.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.dto.bill.BillRequest;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.AccountRepository;
import com.casemodule6_be.repository.BillDetailRepository;
import com.casemodule6_be.repository.BillRepository;
import com.casemodule6_be.repository.RoomRepository;
import com.casemodule6_be.service.bill.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final AccountRepository accountRepository;
    private final BillDetailRepository billDetailRepository;
    private final RoomRepository roomRepository;


    public List<Bill> findBillByAccountId(Long accountId) {
        return billRepository.findAllByAccountId(accountId);
    }




    public Bill save(BillRequest billRequest) {

        Long userId = billRequest.getIdAccount();
        Optional<Account> account = accountRepository.findById(userId);
        if(!account.isPresent()){
            throw  new SSWException(EnumSSWException.ACCOUNT_NOT_EXISTED);
        }

//        Tính tổng giá tiền thuê
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date date1 = Date.valueOf(billRequest.getCheckinDate());
        Date date2 = Date.valueOf(billRequest.getCheckoutDate());
        c1.setTime(date1);
        c2.setTime(date2);
        Double amountDay = Double.valueOf((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
        Double priceRoom = roomRepository.findById(billRequest.getIdRoom()).get().getPrice();
        Double total = priceRoom*amountDay;


//    Save Bill
        Bill bill =  new Bill();
        bill.setDate(billRequest.getDate());
        bill.setAccount(accountRepository.findById(userId).get());
        bill.setTotal(total);
        bill.setStatus(Constant.DEFAULT_STATUS);
        Bill newBill = billRepository.save(bill);

//      Save BillDetail
        Optional<Room> room = roomRepository.findById(billRequest.getIdRoom());
        if(!room.isPresent()){
            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
        }
        BillDetail billDetail = new BillDetail();
        billDetail.setBill(newBill);
        billDetail.setRoom(roomRepository.findById(billRequest.getIdRoom()).get());
        billDetail.setCheckIn(java.sql.Date.valueOf(billRequest.getCheckinDate()));
        billDetail.setCheckOut(java.sql.Date.valueOf(billRequest.getCheckoutDate()));
        billDetail.setStatus(Constant.DEFAULT_STATUS);
        billDetailRepository.save(billDetail);

        return bill;
    }


}
