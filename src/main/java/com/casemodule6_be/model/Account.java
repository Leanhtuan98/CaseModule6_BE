package com.casemodule6_be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    @ManyToMany
    private List<Role> roles;
    private Boolean status;
}
