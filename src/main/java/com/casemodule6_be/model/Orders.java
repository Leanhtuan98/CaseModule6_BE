package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double total;
    private int amountDay;

    @ManyToOne
    private Account account;
    private Boolean status;

}
