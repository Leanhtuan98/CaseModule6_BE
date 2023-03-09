package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkIn;
    private Date checkOut;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Orders order;
    private Boolean status;
}
