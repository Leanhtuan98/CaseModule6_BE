package com.casemodule6_be.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SSWExceptionResponse {

    private Integer httpStatusCode;
    private String httpStatusName;
    private String errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private SSWExceptionMsg sswExceptionMsg;

    public SSWExceptionResponse(HttpStatus httpStatus, String errorCode, SSWExceptionMsg sswExceptionMsg) {
        this.httpStatusCode = httpStatus.value();
        this.httpStatusName = httpStatus.name();
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.sswExceptionMsg = sswExceptionMsg;
    }
}
