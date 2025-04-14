package com.ferrara.tool.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "NO CODE"),
    INCORRECT_PASSWORD(300, HttpStatus.BAD_REQUEST,"CURRENT PASSWORD IS INCORRECT"),
    PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST,"CURRENT PASSWORD DOES NOT MATCH"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "USER ACCOUNT IS LOCKED"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "USER ACCOUNT IS DISABLED"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "USERNAME/EMAIL AND / OR PASSWORD IS INCORRECT"),
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
