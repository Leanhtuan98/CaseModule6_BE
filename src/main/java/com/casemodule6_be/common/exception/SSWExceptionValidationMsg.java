package com.casemodule6_be.common.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SSWExceptionValidationMsg {

    private String objectName;
    private String field;
    private String messageDefault;

}
