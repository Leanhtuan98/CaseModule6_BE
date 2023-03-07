package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double total;
    private Double deposit;
    @ManyToOne
    private Account account;

}
