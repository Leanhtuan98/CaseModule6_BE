package com.casemodule6_be.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("true")
    private boolean status;


    public Room() {
    }


}
