package com.casemodule6_be.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SSWExceptionValidationResponse {

    private Integer httpStatusCode;
    private String httpStatusName;
    private String errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private List<SSWExceptionValidationMsg> errorDetails;

    public SSWExceptionValidationResponse(HttpStatus httpStatus, String errorCode, List<SSWExceptionValidationMsg> errorDetails) {
        this.httpStatusCode = httpStatus.value();
        this.httpStatusName = httpStatus.name();
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.errorDetails = errorDetails;
    }
}
