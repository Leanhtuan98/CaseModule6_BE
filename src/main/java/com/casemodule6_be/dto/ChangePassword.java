package com.casemodule6_be.dto;

import lombok.Data;

@Data

public class ChangePassword {
    private Long Id;
    private String currentPass;
    private String newPass;
    private String confirmPass;
}
