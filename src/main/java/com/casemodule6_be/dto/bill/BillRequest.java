package com.casemodule6_be.dto.bill;

import lombok.Data;

import java.sql.Date;


@Data
public class BillRequest {
    private Long idAccount;
    private Date date;
    private Long idRoom;
    private String checkinDate;
    private String checkoutDate;
    private Integer status;

}
