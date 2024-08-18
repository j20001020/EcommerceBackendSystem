package com.kai.ecommercebackendsystem.dto.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private Integer status;

    private String message;

    private T data;

    public static <T> ApiResponse<T> of(ResponseStatus status, String message, T data) {
        var response = new ApiResponse<T>();
        response.status = status.getValue();
        response.message = message;
        response.data = data;

        return response;
    }
}
