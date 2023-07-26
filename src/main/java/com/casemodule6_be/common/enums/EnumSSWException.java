package com.casemodule6_be.common.enums;

import org.springframework.http.HttpStatus;

public enum EnumSSWException {


    ADDRESS_NOT_EXISTED("ADDRESS_NOT_EXISTED", HttpStatus.NOT_FOUND, "address.not-existed",
            "Không tìm thấy địa chỉ"),
    CATEGORY_NOT_EXISTED("CATEGORY_NOT_EXISTED", HttpStatus.NOT_FOUND, "category.not-existed",
            "Không tìm thấy loại phòng"),
    COMMENT_NOT_EXISTED("COMMENT_NOT_EXISTED", HttpStatus.NOT_FOUND, "comment.not-existed",
            "Không tìm thấy comment"),
    ACCOUNT_NOT_EXISTED("ACCOUNT_NOT_EXISTED", HttpStatus.NOT_FOUND, "user.not-existed",
            "Không tìm thấy user"),
    USER_EXISTED("USER_EXISTED", HttpStatus.NOT_FOUND, "user.existed",
            "User đã tồn tại"),
    EMAIL_EXISTED("EMAIL_EXISTED", HttpStatus.NOT_FOUND, "email.existed",
            "Email đã tồn tại"),
    ROOM_NOT_EXISTED("ROOM_NOT_EXISTED", HttpStatus.NOT_FOUND, "room.not-existed",
            "Không tìm thấy room"),

    ;

    private String errorCode;

    private HttpStatus httpStatus;

    private String messageKey;

    private String messageDefault;

    EnumSSWException(String errorCode, HttpStatus httpStatus, String messageKey, String messageDefault) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
        this.messageDefault = messageDefault;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getMessageDefault() {
        return messageDefault;
    }

    @Override
    public String toString() {
        return "EnumCommonException{" +
                "errorCode='" + errorCode + '\'' +
                ", httpStatus=" + httpStatus +
                ", messageKey='" + messageKey + '\'' +
                ", messageDefault='" + messageDefault + '\'' +
                '}';
    }
}
