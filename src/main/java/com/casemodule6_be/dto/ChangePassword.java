package com.casemodule6_be.dto;

import lombok.Data;

@Data

public class ChangePassword {
    private long id;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
