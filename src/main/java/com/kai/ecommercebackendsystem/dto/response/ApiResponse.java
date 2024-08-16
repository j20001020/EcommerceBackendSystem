package com.kai.ecommercebackendsystem.dto.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private Integer status;

    private String message;

    private T data;

    public ApiResponse(ResponseStatus status, String message, T data) {
        this.status = status.getValue();
        this.message = message;
        this.data = data;
    }
}
