package com.kai.ecommercebackendsystem.dto.response;

public enum ResponseStatus {
    OK(200),
    NOT_FOUND(404),
    BAD_REQUEST(400);

    private final int value;

    ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
