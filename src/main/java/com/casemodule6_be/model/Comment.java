package com.casemodule6_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String content;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")

    private Account account;


    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")

    private Room room;

    private Long status;

}
