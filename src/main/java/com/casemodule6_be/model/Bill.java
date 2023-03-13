package com.casemodule6_be.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private Date date;
    private Double total;
    @ManyToOne
    private Account account;
    private Boolean status;
}
