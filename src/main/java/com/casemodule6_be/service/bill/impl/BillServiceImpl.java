package com.casemodule6_be.service.bill.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.BillDTO;
import com.casemodule6_be.dto.DataDTO;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.repository.AccountRepository;
import com.casemodule6_be.repository.BillDetailRepository;
import com.casemodule6_be.repository.IBillRepo;
import com.casemodule6_be.repository.RoomRepository;
import com.casemodule6_be.service.bill.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillServiceImpl implements BillService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IBillRepo iBillRepo;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BillDetailRepository billDetailRepository;
    @Autowired
    RoomRepository roomRepository;



//    public List<BillProjection> showTotalBill() {
//        return iBillRepo.showTotalBill();
//    }

    public List<Bill> findBillByAccountId(Long accountId) {
        return iBillRepo.findAllByAccount_Id(accountId);
    }

    public Bill save(BillDTO billDTO) {
        Long userId = billDTO.getIdAccount();
        Double total = billDTO.getTotalPrice();
        List<DataDTO> dataRent = billDTO.getData();
        Bill bill = null;
        if (!dataRent.isEmpty()) {

            bill = new Bill();
            bill.setDate(billDTO.getDate());
            bill.setAccount(accountRepository.findById(userId).get());
            bill.setTotal(total);
            Bill newBill = iBillRepo.save(bill);

            for (DataDTO data : dataRent) {

                BillDetail billDetail = new BillDetail();
                billDetail.setBill(newBill);
                billDetail.setRoom(roomRepository.findById(data.getId()).get());
                billDetail.setCheckIn(java.sql.Date.valueOf(data.getCheckinDate()));
                billDetail.setCheckOut(java.sql.Date.valueOf(data.getCheckoutDate()));
                billDetail.setStatus(Constant.DEFAULT_STATUS);
                billDetailRepository.save(billDetail);
            }

        }
        return bill;
    }
}
