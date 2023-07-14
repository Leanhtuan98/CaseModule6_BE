package com.casemodule6_be.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {
    private Long id;
    private String checkinDate;
    private String checkoutDate;
    private Integer status;

}
