package com.casemodule6_be.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 80)
    private String name;
    @NotBlank
    @Size(min = 3, max = 80)
    private String username;
    @NotBlank
    @Size(min = 3, max = 80)
    @Email
    private String email;
    @NotBlank
    @Size(min = 3, max = 80)
    private String password;

    private Set<String> roles;


}
