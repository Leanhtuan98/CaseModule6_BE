package com.casemodule6_be.service;

import com.casemodule6_be.dto.BillDTO;
import com.casemodule6_be.projection.BillProjection;
import com.casemodule6_be.dto.DataDTO;
import com.casemodule6_be.model.Bill;
import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.repository.IAccountRepo;
import com.casemodule6_be.repository.IBillDetailRepo;
import com.casemodule6_be.repository.IBillRepo;
import com.casemodule6_be.repository.IRoomRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IBillRepo iBillRepo;
    @Autowired
    IAccountRepo iAccountRepo;
    @Autowired
    IBillDetailRepo iBillDetailRepo;
    @Autowired
    IRoomRepo iRoomRepo;



    public List<BillProjection> showTotalBill() {
        return iBillRepo.showTotalBill();
    }

    public List<Bill> findBillByAccountId(long accountId) {
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
            bill.setAccount(iAccountRepo.findById(userId).get());
            bill.setTotal(total);
            Bill newBill = iBillRepo.save(bill);

            for (DataDTO data : dataRent) {

                BillDetail billDetail = new BillDetail();
                billDetail.setBill(newBill);
                billDetail.setRoom(iRoomRepo.findById(data.getId()).get());
                billDetail.setCheckIn(java.sql.Date.valueOf(data.getCheckinDate()));
                billDetail.setCheckOut(java.sql.Date.valueOf(data.getCheckoutDate()));
                billDetail.setStatus(true);
                iBillDetailRepo.save(billDetail);
            }

        }
        return bill;
    }
}
