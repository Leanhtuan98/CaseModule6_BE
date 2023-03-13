package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
