package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Room room;
    private Boolean status;
}
