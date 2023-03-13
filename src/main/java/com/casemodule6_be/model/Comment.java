package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int rating;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Room room;
    private Boolean status;

}
