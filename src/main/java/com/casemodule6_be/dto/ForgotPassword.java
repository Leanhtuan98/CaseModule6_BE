package com.casemodule6_be.dto;

import lombok.Data;

@Data
public class ForgotPassword {
    private Long id;
    private String email;
    private String newPassword;
    private String confirmPassword;
}
