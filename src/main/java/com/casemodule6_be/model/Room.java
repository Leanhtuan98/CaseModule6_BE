package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String addressRoom;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Category category;

    @ManyToOne
    private Address address;
    private Boolean status;
}
