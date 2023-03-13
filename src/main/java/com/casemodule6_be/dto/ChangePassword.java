package com.casemodule6_be.dto;

import lombok.Data;

@Data

public class ChangePassword {
    private String currentPass;
    private String newPass;
    private String confirmPass;
}
