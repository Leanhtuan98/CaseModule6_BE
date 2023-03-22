package com.casemodule6_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {
    private long id;
    private String checkinDate;
    private String checkoutDate;
    private boolean status;

}
