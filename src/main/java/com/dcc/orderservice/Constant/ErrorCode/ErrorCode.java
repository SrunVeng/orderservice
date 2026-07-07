package com.dcc.orderservice.Constant.ErrorCode;
import lombok.Getter;

@Getter
public enum ErrorCode {



    INVALID_CREDENTIAL("ERROR001","INVALID CREDENTAIL 401"),
    ORDER_NOT_FOUND("ORDER001", "ORDER NOT FOUND");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;

    }
}
