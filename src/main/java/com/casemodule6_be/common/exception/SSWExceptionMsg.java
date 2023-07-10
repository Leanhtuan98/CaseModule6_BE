package com.casemodule6_be.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SSWExceptionMsg {

    private String key;
    private String messageDefault;
    private List<String> params;
}
