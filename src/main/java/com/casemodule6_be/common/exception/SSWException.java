package com.casemodule6_be.common.exception;

import com.casemodule6_be.common.enums.EnumSSWException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SSWException extends RuntimeException {

    private EnumSSWException enumSSWException;
    private List<String> msgParams;
    private Exception ex;

    public SSWException(EnumSSWException enumSSWException, List<String> msgParams) {
        super(enumSSWException.getErrorCode());
        this.enumSSWException = enumSSWException;
        this.msgParams = msgParams;
    }

    public SSWException(EnumSSWException enumSSWException) {
        super(enumSSWException.getErrorCode());
        this.enumSSWException = enumSSWException;
        this.msgParams = List.of(enumSSWException.getErrorCode());
    }

    public SSWException(EnumSSWException enumSSWException, Exception ex) {
        super(enumSSWException.getErrorCode());
        this.enumSSWException = enumSSWException;
        this.msgParams = List.of(enumSSWException.getErrorCode());
        this.ex = ex;
    }
}
