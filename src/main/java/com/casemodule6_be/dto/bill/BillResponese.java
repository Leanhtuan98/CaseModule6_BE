package com.casemodule6_be.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponese {
    private Long id;
    private String checkinDate;
    private String checkoutDate;
    private Integer status;

}
