package com.casemodule6_be.dto;

import com.casemodule6_be.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterForm {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String rePassword;
    private List<Role> roles;
}
