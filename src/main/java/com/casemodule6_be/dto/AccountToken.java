package com.casemodule6_be.dto;

import com.casemodule6_be.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String token;

    private List<Role> roles;

}
