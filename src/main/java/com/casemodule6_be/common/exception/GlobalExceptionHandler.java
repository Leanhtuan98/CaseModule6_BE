package com.casemodule6_be.common.exception;

import com.casemodule6_be.common.enums.EnumSSWException;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TRACE_ID_S = "TraceId: %s";
    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {

        String traceId = UUID.randomUUID().toString();
        logException(traceId, (HttpServletRequest) request, ex);

        String errorCode = "MALFORMED_JSON_REQUEST";
        SSWExceptionMsg sswExceptionMsg = new SSWExceptionMsg("exception.malformed-json-request",
                ex.getMessage(),
                List.of(String.format(TRACE_ID_S, traceId)));

        SSWExceptionResponse sswExceptionResponse = new SSWExceptionResponse(status, errorCode, sswExceptionMsg);

        return new ResponseEntity<>(sswExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseEntity<SSWExceptionResponse> handleRuntimeException(Exception e, HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString();

        logException(traceId, request, e);
        SSWExceptionMsg sswExceptionMsg = new SSWExceptionMsg(
                "error.unknown",
                "Lỗi không xác định, vui lòng liên hệ quản trị viên",
                List.of(String.format(TRACE_ID_S, traceId)));

        SSWExceptionResponse sswExceptionResponse = new SSWExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "UnknownError",
                sswExceptionMsg);

        return new ResponseEntity<>(sswExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        String traceId = UUID.randomUUID().toString();

        logException(traceId, (HttpServletRequest) request, ex);

        List<SSWExceptionValidationMsg> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                SSWExceptionValidationMsg detail = SSWExceptionValidationMsg.builder()
                        .objectName(fieldError.getObjectName())
                        .field(fieldError.getField())
                        .messageDefault(fieldError.getDefaultMessage())
                        .build();
                details.add(detail);
            }
        }

        SSWExceptionValidationResponse response = new SSWExceptionValidationResponse(status, "VALIDATION", details);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SSWException.class)
    @ResponseBody
    public ResponseEntity<SSWExceptionResponse> handleSSWException(HttpServletRequest request, SSWException e) {

        String traceId = UUID.randomUUID().toString();
        logException(traceId, request, e);
        EnumSSWException enumSSWException = e.getEnumSSWException();

        List<String> params = new ArrayList<>();

        if (ObjectUtils.isEmpty(e.getMsgParams())) {
            params.addAll(e.getMsgParams());
        }

        params.add(String.format(TRACE_ID_S, traceId));

        return getMsgExceptionResponse(enumSSWException, params, e.getEx());
    }

    private ResponseEntity<SSWExceptionResponse> getMsgExceptionResponse(EnumSSWException enumSSWException, List<String> params, Exception ex) {

        SSWExceptionMsg sswExceptionMsg = null;

        if (!ObjectUtils.isEmpty(ex)) {
            sswExceptionMsg = new SSWExceptionMsg(enumSSWException.getMessageKey(),
                    ex.getMessage(), params);
        } else {
            sswExceptionMsg = new SSWExceptionMsg(
                    enumSSWException.getMessageKey(),
                    enumSSWException.getMessageDefault(),
                    params);
        }

        SSWExceptionResponse sswExceptionResponse = new SSWExceptionResponse(
                enumSSWException.getHttpStatus(),
                enumSSWException.getErrorCode(),
                sswExceptionMsg);

        return new ResponseEntity<>(sswExceptionResponse, enumSSWException.getHttpStatus());
    }

    private void logException(String traceId, HttpServletRequest request, Exception ex) {
        if (!ObjectUtils.isEmpty(request)) {
            String url = request.getRequestURI();
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            String stringQuery = request.getQueryString();
            Enumeration<String> headerName = requestWrapper.getHeaderNames();
            HashMap<String, String> headers = new HashMap<>();
            while (headerName.hasMoreElements()) {
                String key = headerName.nextElement();
                headers.put(key, requestWrapper.getHeader(key));
            }
            String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());

            L.error(String.format("""
                    TraceId: %s
                    URL: %s
                    QueryParam: %s
                    RequestHeader: %s
                    RequestBody: %s""", traceId, url, stringQuery, headers, requestBody), ex);

        }
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {

        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            L.error("getStringValues", e);
        }

        return "";
    }
}
