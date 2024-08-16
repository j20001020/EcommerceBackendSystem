package com.kai.ecommercebackendsystem.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private ExceptionType type;

    private String info;

    public static ExceptionResponse of(ExceptionType type, String info) {
        var response = new ExceptionResponse();
        response.type = type;
        response.info = info;

        return response;
    }

}
